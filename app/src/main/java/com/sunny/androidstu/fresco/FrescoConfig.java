package com.sunny.androidstu.fresco;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.sunny.baselib.log.MLogTag;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhangxin17 on 2019/7/11
 */
public class FrescoConfig {

    private static final String TAG = MLogTag.TAG_FRESCO + "FrescoConfig";

    public static void init(Context context) {

        // 磁盘配置
        final MemoryCacheParams memoryCacheParams = new MemoryCacheParams(
                20 * ByteConstants.MB, // 最大内存占用
                256, // 内存中图片最大数量
                15 * ByteConstants.MB, // 准备删除的内存大小
                256, // 准备删除的最大个数
                2 * ByteConstants.MB // 单个图片的最大大小
        );
        Supplier<MemoryCacheParams> bitmapCacheParamsSupplier = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return memoryCacheParams;
            }
        };

        // 内存配置
        DiskCacheConfig mainDiskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setMaxCacheSize(40 * ByteConstants.MB) // 最大磁盘空间
                .setMaxCacheSizeOnLowDiskSpace(10 * ByteConstants.MB) // 磁盘空间不足时的最大内存
                .setMaxCacheSizeOnVeryLowDiskSpace(2 * ByteConstants.MB) // 磁盘空间极低时的最大内存
//                .setDiskTrimmableRegistry(MyMemoryTrimmableRegistry.getInstance()) // 磁盘空间不足时管理
                .build();

        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());

        //
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setBitmapMemoryCacheParamsSupplier(bitmapCacheParamsSupplier) // 图片内存
//                .setSmallImageDiskCacheConfig(mainDiskCacheConfig) // 小图片磁盘缓存，通过ImageRequest中的ImageType=SMALL进行区分
                .setMainDiskCacheConfig(mainDiskCacheConfig) // 默认磁盘缓存
//                .setMemoryTrimmableRegistry(MyMemoryTrimmableRegistry.getInstance()) // 内存空间不足时的管理
                .setRequestListeners(requestListeners) // 日志输出

//                .setCacheKeyFactory(cacheKeyFactory)
//                .setDownsampleEnabled(true) // 下载缩略图
//                .setWebpSupportEnabled(true) // 支持webP格式图片
//                .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)
//                .setImageCacheStatsTracker(imageCacheStatsTracker)
//                .setNetworkFetchProducer(networkFetchProducer)
//                .setPoolFactory(poolFactory)
//                .setProgressiveJpegConfig(progressiveJpegConfig)
//                .setRequestListeners(requestListeners)
//                .setSmallImageDiskCacheConfig(smallImageDiskCacheConfig)
                .build();

        Fresco.initialize(context, config);

        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
    }


    public static void clearCache() {

        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        // 是否在内存中
//        imagePipeline.isInBitmapMemoryCache(Uri.parse(""));

        // 是否在磁盘上
//        imagePipeline.isInDiskCache(Uri.parse(""));

        // 清除内存数据
//        imagePipeline.clearMemoryCaches();

        // 清除磁盘数据
//        imagePipeline.clearDiskCaches();

        // 清除所有数据，是上面两条命令的集合
        imagePipeline.clearCaches();
    }

}
