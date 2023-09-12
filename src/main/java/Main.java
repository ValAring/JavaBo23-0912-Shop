public class Main{
    public static void main(String[] args){
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("1", "Book"));
        productRepo.addProduct(new Product("3", "Manga"));
        productRepo.addProduct(new Product("4", "Comic"));
        productRepo.addProduct(new Product("5", "Zeitschrift"));

        ShopService shopService = new ShopService(productRepo, new OrderMapRepo());




    }
}
