
class Cake {
    private final double sugar;
    private final double butter;
    private final int eggs;
    private final double flour;
    private final double bakingpowder;
    private final double milk;
    private final int cherry;

    public Cake(CakeBuilder builder) {
        this.sugar = builder.sugar;
        this.butter = builder.butter;
        this.eggs = builder.eggs;
        this.flour = builder.flour;
        this.bakingpowder = builder.bakingpowder;
        this.milk = builder.milk;
        this.cherry = builder.cherry;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "sugar=" + sugar +
                ", butter=" + butter +
                ", eggs=" + eggs +
                ", flour=" + flour +
                ", bakingpowder=" + bakingpowder +
                ", milk=" + milk +
                ", cherry=" + cherry +
                '}';
    }

    public static class CakeBuilder {
        private double sugar;
        private double butter;
        private int eggs;
        private double flour;
        private double bakingpowder;
        private double milk;
        private int cherry;

        public CakeBuilder sugar(double quantity) {
            this.sugar = quantity;
            return this;
        }

        public CakeBuilder butter(double quantity) {
            this.butter = quantity;
            return this;
        }

        public CakeBuilder eggs(int quantity) {
            this.eggs = quantity;
            return this;
        }

        public CakeBuilder flour(double quantity) {
            this.flour = quantity;
            return this;
        }

        public CakeBuilder bakingpowder(double quantity) {
            this.bakingpowder = quantity;
            return this;
        }

        public CakeBuilder milk(double quantity) {
            this.milk = quantity;
            return this;
        }

        public CakeBuilder cherry(int quantity) {
            this.cherry = quantity;
            return this;
        }

        public Cake build() {
            return new Cake(this);
        }
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Cake cake = new Cake.CakeBuilder()
                .sugar(1)
                .butter(0.5)
                .eggs(2)
                .flour(1.5)
                .bakingpowder(0.75)
                .milk(0.5)
                .build();
        System.out.println(cake);
    }
}
