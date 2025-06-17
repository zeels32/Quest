package com.quests.demo.products.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quests.demo.products.data.local.dao.ProductDao
import com.quests.demo.products.data.local.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao



}