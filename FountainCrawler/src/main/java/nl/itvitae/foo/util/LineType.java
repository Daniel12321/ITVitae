package nl.itvitae.foo.util;

public enum LineType {

    ERROR(Color.RED),
    WARNING(Color.PURPLE),
    MONSTER(Color.RED),
    ROOM(Color.BLUE),
    HINT(Color.CYAN),
    REGEN(Color.GREEN);

    private Color color;

    LineType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String makeLine(String text) {
        return this.color + text + Color.RESET;
    }

    public void print(String text) {
        System.out.println(this.makeLine(text));
    }
}
