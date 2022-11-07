package com.my.sadebuser.act.network.request.category;

import com.my.sadebuser.act.model.category.CategoryResponse;
import com.my.sadebuser.model.SuccessResGetCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryRequest {

//@Query("provider_name") String provider_name
//    @GET("get_category")
//    Call<CategoryResponse> getCategory();

//    @GET("get_category")
//    Call<CategoryResponse> getCategory(@Query("lang") String provider_name);

//    @GET("get_all_service")
//    Call<CategoryResponse> getCategory(@Query("lang") String provider_name);

    @GET("get_business_type")
    Call<CategoryResponse> getCategory(@Query("lang") String provider_name);

}
/*{
result: [
{
id: "1",
category_name: "Food",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "2",
category_name: "Pets",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "3",
category_name: "Containers",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "4",
category_name: "Appliances",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "5",
category_name: "Vessels",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "6",
category_name: "Luggage",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "7",
category_name: "Fragile",
image: "",
date_time: "2020-03-04 08:18:03",
},
{
id: "16",
category_name: "Special cargo",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "15",
category_name: "Vehiculos",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "14",
category_name: "Mail",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "13",
category_name: "Pallets",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "12",
category_name: "Furniture",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "11",
category_name: "Machinery",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "19",
category_name: "Bulk",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "9",
category_name: "Livestock",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "8",
category_name: "Liquids",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "17",
category_name: "Full load",
image: "",
date_time: "2020-03-04 14:18:03",
},
{
id: "18",
category_name: "Removal",
image: "",
date_time: "2020-03-04 14:18:03",
},
],
message: "successfull",
status: "1",
}
*/