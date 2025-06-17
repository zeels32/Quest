package com.quests.demo.presentation.util

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy

object CoilImageLoader {

    @Volatile
    private var imageLoader: ImageLoader? = null

    fun getImageLoader(context: Context): ImageLoader {
        return imageLoader ?: synchronized(this) {
            imageLoader ?: ImageLoader.Builder(context)
                .crossfade(true)
                .respectCacheHeaders(false)
                .memoryCache {
                    MemoryCache.Builder(context)
                        .maxSizePercent(0.25) // Use 25% of the app's memory for caching images
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(context.cacheDir.resolve("image_cache"))
                        .maxSizeBytes(100 * 1024 * 1024)
                        .build()
                }
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build().also { imageLoader = it }
        }
    }

}