# javaweb_blog 
使用servlet，jsp，java及ajax完成的一个博客项目，软件为myeclipse8.5，JDK版本为1.7，mysql版本为5.6,，tomcat版本为6.0

blog.sql文件是mysql的数据库，直接导入就行了

jar包地址：https://oss.qqgg.xyz/jar%E5%8C%85.zip

也可以自己去相关地址下载：
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gcml42j1znj309i08it8v.jpg)


由于是第一次解除javaweb项目，经验不足，写的很乱，导致自己都有点看不下去（后台模板及图片资源均值来源网络）

特别感谢我们敬爱的班主任-胡老师，感谢老师的教导

### 前台首页：(自己写的，布局参考的ARMX & VIRCLOUD.----基于typecho的一款特别好看的主题，使用了一言接口，ajax加载下一页(两种方式)，ajax评论，实现方式不太友好，代码在servlet里面)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqld7zhkj31hc0q7nla.jpg)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqlnv2pzj31hc0q7dkl.jpg)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqm9dse3j31hc0q5aij.jpg)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqnt9vkwj31hc0q6q6w.jpg)


### 登录页面：(ajax判断验证码是否输入正确，过滤器自动登录)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckpyfy2qkj31hc0q6wk3.jpg)

### 后台首页：(使用cookie记录登录次数和登录时间及ip地址)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckq1fknatj31hc0q6td2.jpg)

### 头像设置：(接口来源网络,图片以base64保存在数据库中)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckq5z4s8vj31hc0q7437.jpg)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckq7clwexj31hc0smdwu.jpg)

### 文章列表：(实现分页，搜索，标题及内容截取，文章上移)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckq8966h3j31hc0q6q7d.jpg)

### 添加文章：(使用百度编辑器，实现文章首页图片上传)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqch1ol6j31hc0q542f.jpg)

### 编辑文字：(首页图片预览及下载)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqe62s3oj31hc0q7td5.jpg)

### 评论列表：(实现隐藏及显示，可后台回复评论)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqgmuevej31hc0q70wu.jpg)
![image.png](http://ww1.sinaimg.cn/large/005KupFKgy1gckqgzliivj31hc0qdacw.jpg)

