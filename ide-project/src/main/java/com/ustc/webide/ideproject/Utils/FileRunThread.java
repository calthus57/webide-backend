package com.ustc.webide.ideproject.Utils;
/*
//1):定义一个类A继承于java.lang.Thread类.
class MusicThread extends Thread{
    //2):在A类中覆盖Thread类中的run方法.
    public void run() {
        //3):在run方法中编写需要执行的操作
        for(int i = 0; i < 50; i ++){
            System.out.println("播放音乐"+i);

        }
    }
}*/
public class FileRunThread extends Thread{
    private Integer status=0;
    private String source=null;
    private  String target=null;//欢迎使用 renren-fast 人人快速开发平台 - Powered By https://www.renren.io/
    @Override
    public void run(){
        if(source!=null&&target!=null){
            status = 1;
            try {
                FileAndPackageUtil.copy(source,target);
                source = null;
                target = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            status = 0;
        }
        else System.out.println("No soucer and target!");
    }
    public static void main(String[] args) throws InterruptedException {

        for(int j = 0; j < 50; j ++){
            System.out.println("运行游戏"+j);
            Thread.sleep(10);
            if(j == 10){
                //4):在main方法(线程)中,创建线程对象,并启动线程.
                FileRunThread music = new FileRunThread();
                music.source= ("D:\\WebIDE\\ide\\userFile\\312349");
                music.target=("D:\\WebIDE\\ide\\userFile\\Angularproject");
                music.start();
            }
        }
    }
}
