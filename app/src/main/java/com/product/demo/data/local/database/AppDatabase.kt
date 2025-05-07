package com.product.demo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.product.demo.data.local.dao.ProductDao
import com.product.demo.data.local.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao



}