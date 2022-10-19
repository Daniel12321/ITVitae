package nl.itvitae.foo.monster;

import com.google.gson.Gson;
import nl.itvitae.foo.exception.MonsterParsingError;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class MonsterParser {

    public static void parse(String file) {

        List<MonsterType> l = new ArrayList<>();
        MonsterType m = new MonsterType();
        m.setName("Test");
        m.setMinDmg(1);
        m.setMaxDmg(2);
        m.setMinHp(3);
        m.setMaxHp(4);
        l.add(m);
        MonsterTypes types = new MonsterTypes();
        types.setTypes(l);


        Gson gson = new Gson();

        System.out.println(gson.toJson(l));


        try (InputStream in = MonsterParser.class.getClassLoader().getResourceAsStream(file);
             Reader reader = new InputStreamReader(in)) {
            MonsterType.TYPES.addAll(gson.fromJson(reader, MonsterTypes.class).getTypes());
        } catch (Exception exc) {
            throw new MonsterParsingError(exc);
        }
    }
}
