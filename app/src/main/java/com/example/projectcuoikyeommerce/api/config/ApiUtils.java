package com.example.projectcuoikyeommerce.api.config;

import com.example.projectcuoikyeommerce.api.BannerController;
import com.example.projectcuoikyeommerce.api.CartController;
import com.example.projectcuoikyeommerce.api.DescriptionController;
import com.example.projectcuoikyeommerce.api.ImageController;
import com.example.projectcuoikyeommerce.api.NotifycationController;
import com.example.projectcuoikyeommerce.api.OderController;
import com.example.projectcuoikyeommerce.api.ProductController;
import com.example.projectcuoikyeommerce.api.SendNotificationController;
import com.example.projectcuoikyeommerce.api.TagChildController;
import com.example.projectcuoikyeommerce.api.TagParentController;
import com.example.projectcuoikyeommerce.api.TrademarkController;
import com.example.projectcuoikyeommerce.api.UserController;
import com.example.projectcuoikyeommerce.api.UserInfoController;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.model.Product;

public class ApiUtils {
    public static final String url = URL.BASE_URL+"api/";
    public static TagParentController tagParent(){
        return ConfigRetrofit.getClient(url).create(TagParentController.class);
    }
    public static SendNotificationController notification(){
        return ConfigRetrofit.getClient("https://fcm.googleapis.com/").create(SendNotificationController.class);
    }
    public static TagChildController tagChild(){
        return ConfigRetrofit.getClient(url).create(TagChildController.class);
    }
    public static UserController user(){
        return ConfigRetrofit.getClient(url).create(UserController.class);
    }
    public static BannerController banner(){
        return ConfigRetrofit.getClient(url).create(BannerController.class);
    }
    public static ProductController product(){
        return ConfigRetrofit.getClient(url).create(ProductController.class);
    }
    public static TrademarkController trademark(){
        return ConfigRetrofit.getClient(url).create(TrademarkController.class);
    }
    public static ImageController image(){
        return ConfigRetrofit.getClient(url).create(ImageController.class);
    }
    public static DescriptionController description(){
        return ConfigRetrofit.getClient(url).create(DescriptionController.class);
    }
    public static CartController cart(){
        return ConfigRetrofit.getClient(url).create(CartController.class);
    }
    public static UserInfoController address(){
        return ConfigRetrofit.getClient(url).create(UserInfoController.class);
    }
    public static OderController order(){
        return ConfigRetrofit.getClient(url).create(OderController.class);
    }
    public static NotifycationController notifycation(){
        return ConfigRetrofit.getClient(url).create(NotifycationController.class);
    }
}
