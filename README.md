# MyImageLoader
封装的图片加载工具
ImageLoader: 图片缓存类，可以设置需要的缓存策略
ImageCache:抽象了缓存策略接口；
MemoryCache: 实现了内存缓存;
DiskCache: 实现了磁盘缓存；
DoubleCache: 实现了内存和磁盘两种缓存共用的策略；
CloseUtils类：实现了关闭Closeable对象的方法，如FileOutputStream对象等
