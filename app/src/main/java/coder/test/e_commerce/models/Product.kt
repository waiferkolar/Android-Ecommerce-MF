package coder.test.e_commerce.models

data class Product(
    val id : Int,
    val cat_id : Int,
    val name : String,
    val price : Int,
    val image : String,
    val description : String,
    val created_at : String,
    val updated_at : String
)