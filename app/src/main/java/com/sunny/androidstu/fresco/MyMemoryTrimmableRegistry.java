package com.sunny.androidstu.fresco;

import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;

/**
 * Created by zhangxin17 on 2019/7/17
 * 通知内存和磁盘的持有者进行内存清理
 */
class MyMemoryTrimmableRegistry implements MemoryTrimmableRegistry, DiskTrimmableRegistry {

    private static MyMemoryTrimmableRegistry sInstance = null;

    public MyMemoryTrimmableRegistry() {
    }

    public static synchronized MyMemoryTrimmableRegistry getInstance() {
        if (sInstance == null) {
            sInstance = new MyMemoryTrimmableRegistry();
        }
        return sInstance;
    }

    @Override
    public void registerMemoryTrimmable(MemoryTrimmable trimmable) {

    }

    @Override
    public void unregisterMemoryTrimmable(MemoryTrimmable trimmable) {

    }

    @Override
    public void registerDiskTrimmable(DiskTrimmable trimmable) {

    }

    @Override
    public void unregisterDiskTrimmable(DiskTrimmable trimmable) {

    }
}
