package com.maxi.weixiao;

import java.util.List;

/**
 * Created by mingzhi.yuan on 3/19/16.
 */
public class NetInterface {
    public interface register{
        public void registerCallBack(boolean result);

    }

    public interface Login{
        public void loginCallBack(boolean result);

    }

    public interface Bookshelf{
        public void callBack(List<Novel> list);
    }

    public interface InterfaceGetLibrary{
        public void getLibraly(List<Novel> list);
    }


}
