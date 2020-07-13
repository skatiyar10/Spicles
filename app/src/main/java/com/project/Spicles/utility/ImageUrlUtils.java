package com.project.Spicles.utility;

import java.util.ArrayList;


public class ImageUrlUtils {
    static ArrayList<String> wishlistImageUri = new ArrayList<>();
    static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> desc = new ArrayList<>();
    static ArrayList<String> price = new ArrayList<>();
    static ArrayList<String> cartListImageUri = new ArrayList<>();
    static ArrayList<String> cart_name = new ArrayList<>();
    static ArrayList<String> cart_desc = new ArrayList<>();
    static ArrayList<String> cart_price = new ArrayList<>();

    public static String[] getImageUrls() {
        String[] urls = new String[] {
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Green-Yellow-Red-Pepper-2009.jpg/180px-Green-Yellow-Red-Pepper-2009.jpg",//capsicum
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Charoli.JPG/180px-Charoli.JPG",//chironji
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Chilly_ap.JPG/180px-Chilly_ap.JPG" , //chilli
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Fennel_seed.jpg/180px-Fennel_seed.jpg", //fennel
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Qasuri_Methi.JPG/180px-Qasuri_Methi.JPG", //kasuri methi
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Harra_%28Terminalia_chebula%29_hanging_fruit_at_23_Mile%2C_Duars%2C_WB_W_IMG_5902.jpg/180px-Harra_%28Terminalia_chebula%29_hanging_fruit_at_23_Mile%2C_Duars%2C_WB_W_IMG_5902.jpg",//inknut, harad
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Mace_1.JPG/180px-Mace_1.JPG",//mace
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/Poppy_seeds.jpg/180px-Poppy_seeds.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/Iran_saffron_threads.jpg/180px-Iran_saffron_threads.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Tamarind2.jpg/180px-Tamarind2.jpg",
                "https://images.pexels.com/photos/479628/pexels-photo-479628.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //wafers
                "https://images.pexels.com/photos/4016512/pexels-photo-4016512.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", //caramel candies
                "https://images.pexels.com/photos/725993/pexels-photo-725993.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //broen pizza
                "https://images.pexels.com/photos/4051597/pexels-photo-4051597.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //berry muffins
                "https://images.pexels.com/photos/3650438/pexels-photo-3650438.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500" //chocolate cupcakes

        };
        return urls;
    }

    public static String[] getOffersUrls() {
        String[] urls = new String[]{
                "https://images.pexels.com/photos/908180/pexels-photo-908180.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //fried viand
                "https://images.pexels.com/photos/33129/popcorn-movie-party-entertainment.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500", //popcorn
                "https://images.pexels.com/photos/566566/pexels-photo-566566.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //boiled egg
                "https://images.pexels.com/photos/479628/pexels-photo-479628.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //wafers
                "https://images.pexels.com/photos/4016512/pexels-photo-4016512.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", //caramel candies
                "https://images.pexels.com/photos/725993/pexels-photo-725993.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //broen pizza
                "https://www.tarladalal.com/td_cont_img/Spicy-Amla-Pickle.JPG",
                "https://www.tarladalal.com/td_cont_img/Methia-Keri.JPG",
                "https://www.tarladalal.com/td_cont_img/Quick-Mango-Chunda.JPG",
                "https://www.tarladalal.com/td_cont_img/Raiwala-Marcha--1.JPG",
                "https://www.tarladalal.com/td_cont_img/Methi-ki-Launji-1.JPG",
                "https://www.tarladalal.com/td_cont_img/Lemon-Pickle-1.JPG",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/black-cardamom_medium.jpg?v=1592416360",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/hing-blog_large.png?v=1547823863",//asafoetida
                "https://cdn.shopify.com/s/files/1/1501/1196/files/curry-leaves-small_large.png?v=1547820781"
        };
        return urls;
    }

    public static String[] getSnacksUrls() {
        String[] urls = new String[]{
                "https://images.pexels.com/photos/327158/pexels-photo-327158.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", //burger
                "https://images.pexels.com/photos/1407346/pexels-photo-1407346.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", //doughnuts
                "https://images.pexels.com/photos/298217/pexels-photo-298217.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //cookies
                "https://images.pexels.com/photos/1582482/pexels-photo-1582482.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //tortilla chips
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSqYmW-TZNEL8161WqcWdZybDBKweZBHgBCmw&usqp=CAU",  //lays
                "https://images.pexels.com/photos/1583884/pexels-photo-1583884.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", //french frie
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSyGCv7EKdtFRWo8500DBZ-VPFyxLEPm8fomA&usqp=CAU",  //samosa
                "https://images.pexels.com/photos/315755/pexels-photo-315755.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"  ,  //pizza
                "https://images.pexels.com/photos/908180/pexels-photo-908180.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //fried viand
                "https://images.pexels.com/photos/33129/popcorn-movie-party-entertainment.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500", //popcorn
                "https://images.pexels.com/photos/566566/pexels-photo-566566.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //boiled egg
                "https://images.pexels.com/photos/479628/pexels-photo-479628.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //wafers
                "https://images.pexels.com/photos/4016512/pexels-photo-4016512.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", //caramel candies
                "https://images.pexels.com/photos/725993/pexels-photo-725993.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //broen pizza
                "https://images.pexels.com/photos/4051597/pexels-photo-4051597.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",  //berry muffins
                "https://images.pexels.com/photos/3650438/pexels-photo-3650438.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500" //chocolate cupcakes
        };
        return urls;
    }

    public static String[] getSpicesUrls() {
        String[] urls = new String[]{
                "https://cdn.shopify.com/s/files/1/1501/1196/files/turmeric-haldi_medium.jpg?v=1592415419", //turmeric
                "https://cdn.shopify.com/s/files/1/1501/1196/files/cumin-jeera_medium.jpg?v=1592415534", //cumin
                "https://cdn.shopify.com/s/files/1/1501/1196/files/cardamom_medium.jpg?v=1592415573", //green cardamon
                "https://cdn.shopify.com/s/files/1/1501/1196/files/coriander_medium.jpg?v=1592416519",//coriander
                "https://cdn.shopify.com/s/files/1/1501/1196/files/Garam-Masala_medium.jpg?v=1592416157",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/ginger_medium.jpg?v=1592416468",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/garlic_medium.jpg?v=1592416571",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/amchoor_medium.jpg?v=1592416756  ",//mango powder
                "https://cdn.shopify.com/s/files/1/1501/1196/files/fenugreek-leaves_medium.jpg?v=1592416641", //fenugreek
                "https://cdn.shopify.com/s/files/1/1501/1196/files/tej-patta-indian-bay_large.jpg?v=1592416739",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/cinnamon-dalachini_medium.jpg?v=1592416842",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/carom-ajwain_medium.jpg?v=1592417562",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/nutmeg_medium.jpg?v=1592417744",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/laung-cloves_medium.jpg?v=1592418135",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/mustard-seed_medium.jpg?v=1592418176",
                "https://cdn.shopify.com/s/files/1/1501/1196/files/black-pepper_medium.jpg?v=1592418214"
        };
        return urls;
    }

    public static String[] getPicklesUrls() {
        String[] urls = new String[]{
                "https://www.tarladalal.com/td_cont_img/Sweet-Lemon-Pickle.JPG", //sweet lemon piclkes
                "https://www.tarladalal.com/td_cont_img/Kishmish_Ka_Murabba_DSC.JPG", //kishmish ka murabba
                "https://www.tarladalal.com/td_cont_img/Garlic-Pickle.JPG", //garlic pickels
                "https://www.tarladalal.com/td_cont_img/Fresh-Turmeric-and-Ginger-Pickle-2.JPG", //turmeeric ginger pickels
                "https://www.tarladalal.com/td_cont_img/Drumstick-Pickle.JPG",
                "https://www.tarladalal.com/td_cont_img/Madras-Onion-Pickle.JPG",
                "https://www.tarladalal.com/td_cont_img/Spicy-Amla-Pickle.JPG",
                "https://www.tarladalal.com/td_cont_img/Methia-Keri.JPG",
                "https://www.tarladalal.com/td_cont_img/Quick-Mango-Chunda.JPG",
                "https://www.tarladalal.com/td_cont_img/Raiwala-Marcha--1.JPG",
                "https://www.tarladalal.com/td_cont_img/Methi-ki-Launji-1.JPG",
                "https://www.tarladalal.com/td_cont_img/Lemon-Pickle-1.JPG",
                "https://www.tarladalal.com/td_cont_img/Punjabi_aam_ka_achaar-2.jpg",
                "https://www.tarladalal.com/members/9306/big/big_carrot_pickle-14498.jpg?size=696X905",//carrot
                "https://www.tarladalal.com/td_cont_img/Lahsun-ka-Achaar.JPG",
                "https://www.tarladalal.com/members/9306/big/big_sweet_and_sour_raw_papaya_pickle-13413.jpg?size=696X905",
        };
        return urls;
    }

    public static String[] getImageName() {
        String[] urls = new String[] {
                "Capsicum",//capsicum
                "Chironji",//chironji
                "Chilli" , //chilli
                "Fennel Seeds", //fennel
                "Kasuri Methi", //kasuri methi
                "Inknut",//inknut, harad
                "Javitri",//mace
                "Poppy Seeds",
                "Saffron",
                "Tamarind",
                "Potato Wafers",  //wafers
                "Caramel Candies", //caramel candies
                "Brown Pizza",  //broen pizza
                "Berry Muffins",  //berry muffins
                "Chocolate Cupcakes" //chocolate cupcakes

        };
        return urls;
    }

    public static String[] getOffersName() {
        String[] urls = new String[]{
                "Fried Viand", //sweet lemon piclkes
                "Popcorns", //kishmish ka murabba
                "Boiled Eggs", //garlic pickels
                "Potato Wafers", //turmeeric ginger pickels
                "Caramel Candies",
                "Brown Pizza",
                "Spicy Amla Pickle",
                "Meethi Keri",
                "Quick Mango Chunda",
                "Raiwala Marcha",
                "Methi ki Launji",
                "Lemon Pickle",
                "Black Cardamon",
                "Asafoetida",//asafoetida
                "Bay Leaf"
        };
        return urls;
    }
    public static String[] getSnacksName() {
        String[] urls = new String[]{
                "Burger", //burger
                "Doughnuts", //doughnuts
                "Cookies",  //cookies
                "Tortilla Chips",  //tortilla chips
                "Lays",  //lays
                "French Fries", //french frie
                "Samosa",  //samosa
                "Pizza"  ,  //pizza
                "Fried Viand",  //fried viand
                "Popcorns", //kishmish ka murabba
                "Boiled Eggs", //garlic pickels
                "Potato Wafers", //turmeeric ginger pickels
                "Caramel Candies",
                "Brown Pizza",  //broen pizza
                "Berry Muffins",  //berry muffins
                "Chocolate Cupcakes" //chocolate cupcakes
        };
        return urls;
    }

    public static String[] getSpicesName() {
        String[] urls = new String[]{
                "Turmeric", //turmeric
                "Cumin Seeds", //cumin
                "Green Cardamon", //green cardamon
                "Coriander",//coriander
                "Garam Masala",
                "Ginger",
                "Garlic",
                "Amchoor",//mango powder
                "Methi Leaves", //fenugreek
                "Tej Patta",
                "Dalchini",
                "Ajwain",
                "Nutmeg",
                "Cloves",
                "Mustard seeds",
                "Black Pepper"
        };
        return urls;
    }

    public static String[] getPicklesName() {
        String[] urls = new String[]{
                "Sweet Lemon Pickles", //sweet lemon piclkes
                "Kishmish ka Murabba", //kishmish ka murabba
                "Garlic Pickle", //garlic pickels
                "Turmeric Ginger Pickle", //turmeeric ginger pickels
                "Drumstick Pickle",
                "Madras Onion Pickle",
                "Spicy Amla Pickle",
                "Methi Keri",
                "Mango Chunda",
                "Raiwala Marcha",
                "Methi ki Launji",
                "Lemon Picles",
                "Aam ka achar",
                "Carrot Pickle",//carrot
                "Lahsun Achar",
                "Papaya Pickle"
        };
        return urls;
    }

    public static String[] getImagedes() {
        String[] urls = new String[] {
                "200gm",//capsicum
                "150gm",//chironji
                "500gm" , //chilli
                "500gm", //fennel
                "200gm", //kasuri methi
                "200gm",//inknut, harad
                "100gm",//mace
                "100gm",
                "10gm",
                "100gm",
                "500gm",  //wafers
                "2 packets", //caramel candies
                "12' large",  //broen pizza
                "10 pieces",  //berry muffins
                "5 pieces" //chocolate cupcakes

        };
        return urls;
    }

    public static String[] getImageprice() {
        String[] urls = new String[] {
                "200",//capsicum
                "350",//chironji
                "200" , //chilli
                "300", //fennel
                "50", //kasuri methi
                "50",//inknut, harad
                "100",//mace
                "150",
                "700",
                "250",
                "100",  //wafers
                "150", //caramel candies
                "500",  //broen pizza
                "275",  //berry muffins
                "300" //chocolate cupcakes

        };
        return urls;
    }
    public static String[] getOffersDes() {
        String[] urls = new String[]{
                "200gm",//capsicum
                "150gm",//chironji
                "500gm" , //chilli
                "500gm", //fennel
                "200gm", //kasuri methi
                "200gm",//inknut, harad
                "100gm",//mace
                "100gm",
                "10gm",
                "100gm",
                "500gm",  //wafers
                "2 packets", //caramel candies
                "12' large",  //broen pizza
                "10 pieces",  //berry muffins
                "5 pieces" //chocolate cupcakes
        };
        return urls;
    }
    public static String[] getOffersPrice() {
        String[] urls = new String[]{
                "200",//capsicum
                "350",//chironji
                "200" , //chilli
                "300", //fennel
                "50", //kasuri methi
                "50",//inknut, harad
                "100",//mace
                "150",
                "700",
                "250",
                "100",  //wafers
                "150", //caramel candies
                "500",  //broen pizza
                "275",  //berry muffins
                "300" //chocolate cupcakes
        };
        return urls;
    }

    public static String[] getSnacksDes() {
        String[] urls = new String[]{
                "200gm",//capsicum
                "150gm",//chironji
                "500gm" , //chilli
                "500gm", //fennel
                "200gm", //kasuri methi
                "200gm",//inknut, harad
                "100gm",//mace
                "100gm",
                "10gm",
                "100gm",
                "500gm",  //wafers
                "2 packets", //caramel candies
                "12' large",  //broen pizza
                "10 pieces",  //berry muffins
                "5 pieces" ,//chocolate cupcakes
                "4 pieces"
        };
        return urls;
    }
    public static String[] getSnacksPrice() {
        String[] urls = new String[]{
                "200",//capsicum
                "350",//chironji
                "200" , //chilli
                "300", //fennel
                "50", //kasuri methi
                "50",//inknut, harad
                "100",//mace
                "150",
                "700",
                "250",
                "100",  //wafers
                "150", //caramel candies
                "500",  //broen pizza
                "275",  //berry muffins
                "300",
                "250"//chocolate cupcakes
        };
        return urls;
    }

    // Methods for Wishlist
    public void addWishlistImageUri(String wishlistImageUri, String name,String desc, String price) {
        this.wishlistImageUri.add(wishlistImageUri);
        this.name.add(name);
        this.desc.add(desc);
        this.price.add(price);
    }

    public void removeWishlistImageUri(int position) {
        this.wishlistImageUri.remove(position);
    }

    public ArrayList<String> getWishlistImageUri(){ return this.wishlistImageUri; }
    public ArrayList<String> getName(){ return this.name; }
    public ArrayList<String> getDesc(){ return this.desc; }
    public ArrayList<String> getPrice(){ return this.price; }

    // Methods for Cart
    public void addCartListImageUri(String cartlistImageUri, String name, String desc, String price) {
        this.cartListImageUri.add(cartlistImageUri);
        this.cart_name.add(name);
        this.cart_desc.add(desc);
        this.cart_price.add(price);
    }

    public void removeCartListImageUri(int position) {
        this.cartListImageUri.remove(position);
    }

    public ArrayList<String> getCartListImageUri(){ return this.cartListImageUri; }
    public ArrayList<String> getCName(){ return this.cart_name; }
    public ArrayList<String> getCDesc(){ return this.cart_desc; }
    public ArrayList<String> getCPrice(){ return this.cart_price; }
}
