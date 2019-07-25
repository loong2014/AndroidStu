package com.sunny.androidstu.fresco;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.sunny.androidstu.R;
import com.sunny.baselib.MResUtils;
import com.sunny.baselib.activity.BaseActivity;
import com.sunny.baselib.log.MLog;
import com.sunny.baselib.log.MLogTag;

/**
 * Created by zhangxin17 on 2019/7/11
 */
public class FrescoActivity extends BaseActivity {
    private static final String TAG = MLogTag.TAG_FRESCO + "FrescoActivity";

    private SimpleDraweeView mSimpleDraweeView;


    private String gifUrl = "http://i3.letvimg.com/lc07_iscms/201905/17/10/21/2510daf6d56c477b9761fc93bb73366a.gif";
    private String jpgUrl = "http://i1.letvimg.com/lc06_iscms/201904/11/14/22/de56b86fe5994805bd85906a20f8f173.jpg";
    private String pngUrl = "http://i3.letvimg.com/lc06_iscms/201902/27/14/45/8444d09a946043b18c8bb5104d188a14.png";

    private String errorUrl = "http://i1.letvimg.com/lc06_iscms/201904/11/14/22/de56b86fe5994885906a20f8f173.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fresco);

        mSimpleDraweeView = findViewById(R.id.my_image_view);


        findViewById(R.id.start_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrescoConfig.clearCache();
            }
        });

        findViewById(R.id.start_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doLoadImgUrl();
            }
        });
    }


    private void doLoadImgUrl() {


        // 1
        Uri uri = Uri.parse(pngUrl);
        mSimpleDraweeView.setImageURI(uri);

//        mSimpleDraweeView.setActualImageResource(R.mipmap.child_def_bg);


//        // 2
//        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
//            @Override
//            public void onFinalImageSet(
//                    String id,
//                    ImageInfo imageInfo,
//                    Animatable anim) {
//                if (imageInfo == null) {
//                    return;
//                }
//                QualityInfo qualityInfo = imageInfo.getQualityInfo();
//
//                logI("Final image received! " + "Size %d x %d" +
//                                " , Quality level %d, good enough: %s, full quality: %s",
//                        imageInfo.getWidth(),
//                        imageInfo.getHeight(),
//                        qualityInfo.getQuality(),
//                        qualityInfo.isOfGoodEnoughQuality(),
//                        qualityInfo.isOfFullQuality());
//            }
//
//            @Override
//            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
//                logI("Intermediate image received");
//            }
//
//            @Override
//            public void onFailure(String id, Throwable throwable) {
//                logI("Error loading %s", id);
//            }
//        };
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setControllerListener(controllerListener)
//                .setUri(jpgUrl)
//                .build();
//
//        mSimpleDraweeView.setController(controller);

        // 3
//        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
//            @Override
//            public String getName() {
//                return "redMeshPostprocessor";
//            }
//
////            @Override
////            public void process(Bitmap bitmap) {
////                for (int x = 0; x < bitmap.getWidth(); x += 2) {
////                    for (int y = 0; y < bitmap.getHeight(); y += 2) {
////                        bitmap.setPixel(x, y, Color.RED);
////                    }
////                }
////            }
//
////            @Override
////            public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
////                for (int x = 0; x < destBitmap.getWidth(); x++) {
////                    for (int y = 0; y < destBitmap.getHeight(); y++) {
////                        destBitmap.setPixel(destBitmap.getWidth() - x, y, sourceBitmap.getPixel(x, y));
////                    }
////                }
////            }
//
//
//            @Override
//            public CloseableReference<Bitmap> process(
//                    Bitmap sourceBitmap,
//                    PlatformBitmapFactory bitmapFactory) {
//                CloseableReference<Bitmap> bitmapRef = bitmapFactory.createBitmap(
//                        sourceBitmap.getWidth() / 2,
//                        sourceBitmap.getHeight() / 2);
//                try {
//                    Bitmap destBitmap = bitmapRef.get();
//                    for (int x = 0; x < destBitmap.getWidth(); x += 2) {
//                        for (int y = 0; y < destBitmap.getHeight(); y += 2) {
//                            destBitmap.setPixel(x, y, sourceBitmap.getPixel(x, y));
//                        }
//                    }
//                    return CloseableReference.cloneOrNull(bitmapRef);
//                } finally {
//                    CloseableReference.closeSafely(bitmapRef);
//                }
//            }
//        };
//
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
//                .setPostprocessor(redMeshPostprocessor)
//                .build();
//
//        PipelineDraweeController controller = (PipelineDraweeController)
//                Fresco.newDraweeControllerBuilder()
//                        .setImageRequest(request)
//                        .setOldController(mSimpleDraweeView.getController())
//                        // other setters as you need
//                        .build();
//        mSimpleDraweeView.setController(controller);

        // 4
//        GenericDraweeHierarchyBuilder builder =
//                new GenericDraweeHierarchyBuilder(getResources());
//        GenericDraweeHierarchy hierarchy = builder
//                .setFadeDuration(300)
//
//
//                // 占位图
//                .setPlaceholderImage(R.mipmap.lechild_default_img)
//
//                // 失败图
//                .setFailureImage(R.mipmap.lechild_default_img)
//
//                // 点击重新加载的图
//                .setRetryImage(R.mipmap.lechild_default_img)
//
//                // 加载进度条
//                .setProgressBarImage(new ProgressBarDrawable() {
//                    @Override
//                    protected boolean onLevelChange(int level) {
//                        logI("onLevelChange  level :" + level);
//                        return super.onLevelChange(level);
//                    }
//                })
//                .build();
//
//        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
//            @Override
//            public void onFinalImageSet(
//                    String id,
//                    ImageInfo imageInfo,
//                    Animatable anim) {
//                if (imageInfo == null) {
//                    return;
//                }
//                QualityInfo qualityInfo = imageInfo.getQualityInfo();
//
//                logI("Final image received! " + "Size %d x %d" +
//                                " , Quality level %d, good enough: %s, full quality: %s",
//                        imageInfo.getWidth(),
//                        imageInfo.getHeight(),
//                        qualityInfo.getQuality(),
//                        qualityInfo.isOfGoodEnoughQuality(),
//                        qualityInfo.isOfFullQuality());
//            }
//
//            @Override
//            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
//                logI("Intermediate image received");
//            }
//
//            @Override
//            public void onFailure(String id, Throwable throwable) {
//                logI("Error loading %s", id);
//            }
//        };
//        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
//            @Override
//            public String getName() {
//                return "redMeshPostprocessor";
//            }
//
////            @Override
////            public void process(Bitmap bitmap) {
////                for (int x = 0; x < bitmap.getWidth(); x += 2) {
////                    for (int y = 0; y < bitmap.getHeight(); y += 2) {
////                        bitmap.setPixel(x, y, Color.RED);
////                    }
////                }
////            }
//
////            @Override
////            public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
////                for (int x = 0; x < destBitmap.getWidth(); x++) {
////                    for (int y = 0; y < destBitmap.getHeight(); y++) {
////                        destBitmap.setPixel(destBitmap.getWidth() - x, y, sourceBitmap.getPixel(x, y));
////                    }
////                }
////            }
//
//
//            @Override
//            public CloseableReference<Bitmap> process(
//                    Bitmap sourceBitmap,
//                    PlatformBitmapFactory bitmapFactory) {
//                CloseableReference<Bitmap> bitmapRef = bitmapFactory.createBitmap(
//                        sourceBitmap.getWidth() / 2,
//                        sourceBitmap.getHeight() / 2);
//                try {
//                    Bitmap destBitmap = bitmapRef.get();
//                    for (int x = 0; x < destBitmap.getWidth(); x += 2) {
//                        for (int y = 0; y < destBitmap.getHeight(); y += 2) {
//                            destBitmap.setPixel(x, y, sourceBitmap.getPixel(x, y));
//                        }
//                    }
//                    return CloseableReference.cloneOrNull(bitmapRef);
//                } finally {
//                    CloseableReference.closeSafely(bitmapRef);
//                }
//            }
//        };
//
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(jpgUrl))
//                .setPostprocessor(redMeshPostprocessor)
//                .build();
//
//
//        ImageRequest lowRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(pngUrl))
//                .setPostprocessor(redMeshPostprocessor)
//                .build();
//
//        PipelineDraweeController controller = (PipelineDraweeController)
//                Fresco.newDraweeControllerBuilder()
//                        .setLowResImageRequest(lowRequest) // 加载低分辨率图
//
//                        .setImageRequest(request) // 加载图片
//
//                        .setTapToRetryEnabled(true) // 加载失败时，默认重试四次
//
//                        .setOldController(mSimpleDraweeView.getController()) // 节省内存
//
//                        .setControllerListener(controllerListener) // 设置监听
//                        .build();
//
//        mSimpleDraweeView.setHierarchy(hierarchy);
//        mSimpleDraweeView.setController(controller);


        // 5
//        Uri localUri = MResUtils.getUriByResId(R.mipmap.child_def_bg);
//
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(localUri)
//                .setLocalThumbnailPreviewsEnabled(true)
//                .build();
//
//        PipelineDraweeController controller = (PipelineDraweeController)
//                Fresco.newDraweeControllerBuilder()
//                        .setImageRequest(request) // 加载图片
//                        .setOldController(mSimpleDraweeView.getController()) // 节省内存
//                        .build();
//
//        mSimpleDraweeView.setController(controller);

        // 6
//        Uri uri = Uri.parse(jpgUrl);
//
//        int width = 100, height = 100;
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
//                .setResizeOptions(new ResizeOptions(width, height))
//                .build();
//
//        PipelineDraweeController controller = (PipelineDraweeController)
//                Fresco.newDraweeControllerBuilder()
//                        .setImageRequest(request) // 加载图片
//                        .setOldController(mSimpleDraweeView.getController()) // 节省内存
//                        .build();
//
//        mSimpleDraweeView.setController(controller);

        // 7
//        Uri uri = Uri.parse(gifUrl);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setAutoPlayAnimations(true)
//                .build();
//        mSimpleDraweeView.setController(controller);

        // 8
//        Uri localUri = MResUtils.getUriByResId(R.mipmap.child_def_bg);
//
//        ImageRequest imageRequest =
//                ImageRequestBuilder.newBuilderWithSource(localUri).build();
//
//        ImagePipeline imagePipeline =
//                Fresco.getImagePipeline();
//
//        // 获取未解码图片
//        DataSource<CloseableReference<CloseableImage>> dataSource =
//                imagePipeline.fetchDecodedImage(imageRequest, null);
//
//        DataSubscriber<CloseableReference<CloseableImage>> dataSubscriber =
//                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
//                    @Override
//                    protected void onNewResultImpl(
//                            DataSource<CloseableReference<CloseableImage>> dataSource) {
//                        if (!dataSource.isFinished()) {
//                            return;
//                        }
//                        CloseableReference<CloseableImage> ref = dataSource.getResult();
//                        if (ref != null) {
//                            try {
//                                CloseableImage image = ref.get();
//                                if (image instanceof CloseableBitmap) {
//                                    // do something with the bitmap
//                                    Bitmap bitmap = ((CloseableBitmap) image).getUnderlyingBitmap();
//
//                                    mSimpleDraweeView.setImageBitmap(bitmap);
//                                }
//
////
////                                // 接收图片
////                                InputStream is = new PooledByteBufferInputStream(result);
////                                try {
////                                    ImageFormat imageFormat = ImageFormatChecker.getImageFormat(is);
////                                    Files.copy(is, path);
////                                } catch (IOException e) {
////
////                                } finally {
////                                    Closeables.closeQuietly(is);
////                                }
//
//                            } finally {
//                                CloseableReference.closeSafely(ref);
//                            }
//                        }
//                    }
//
//                    @Override
//                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
//                        Throwable t = dataSource.getFailureCause();
//                        // handle failure
//                    }
//                };
//
//        dataSource.subscribe(dataSubscriber, UiThreadImmediateExecutorService.getInstance());

    }

    private void logI(String msg) {
        MLog.i(TAG, msg);
    }

    private void logI(String msg, Object... args) {
        logI(formatString(msg, args));
    }

    private static String formatString(String str, Object... args) {
        return String.format(null, str, args);
    }


}
