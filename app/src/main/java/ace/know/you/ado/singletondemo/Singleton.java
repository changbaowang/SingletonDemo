/*
* 文件名：Singleton
* 描    述：单例模式类
* 作    者：Ace
* 时    间：2017-01-23
* 版    权：Ace版权
*/
package ace.know.you.ado.singletondemo;

import java.util.Vector;

public class Singleton {

    //持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
    private static Singleton singleton = null;

    /**
     * 私有构造方法，防止被实例化
     */
    private Singleton() {
        //Load configuration information from DB or file
        //Set values for properties
    }

    /**
     * 多线程锁住
     */
    private static synchronized void synInit() {
        if (singleton == null) {
            singleton = new Singleton();
        }
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synInit();
        }

        return singleton;
    }

    /**
     * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
     *
     * @return
     */
    public Object readResolve() {
        return getInstance();
    }


    //采用"影子实例"的办法为单例对象的属性同步更新
    private Vector properties = null;

    public Vector getProperties() {
        return properties;
    }

    public void updateProperties() {

        //影子实例，但是外部获取不到该实例，
        //在更新属性时，直接生成另一个单例对象实例，
        //这个新生成的单例对象实例将从数据库或文件中读取最新的配置信息；
        //然后将这些配置信息直接赋值给旧单例对象的属性
        Singleton shadow = new Singleton();

        // TODO 让Shadow获取最新配置信息
        
        properties = shadow.getProperties();
    }

}
