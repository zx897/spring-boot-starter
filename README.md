#### 启动步骤

1. 创建数据库(数据库信息见 `/src/main/resources/application.yml`),可根据自己需要修改

2. 启动 
```
 在项目跟目录下执行： ./gradlew bootRun
```

3. 打开浏览器，输入 `http://localhost:8080/`,看到页面显示 `Success!` ,环境搭建成功

### 结果截图：
#### Company API  
GET       /companies    #获取company列表  
![img.png](picture/img.png)   
GET       /companies/1  #获取某个具体company  
![img_1.png](picture/img_1.png)  
GET       /companies/1/employees  #获取某个具体company下所有employee列表  
![img_2.png](picture/img_2.png)  
GET       /companies/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5    
![img_3.png](picture/img_3.png)  
POST      /companies    #增加一个company  
![img_4.png](picture/img_4.png)  
PUT       /companies/1  #更新某个company  
![img_5.png](picture/img_5.png)  
DELETE    /companies/1  #删除某个company以及名下所有employees  
![img_6.png](picture/img_6.png)  
#### employees API  
GET       /employees    #获取employee列表  
![img_7.png](picture/img_7.png)  
GET       /employees/1  #获取某个具体employee  

![img_8.png](picture/img_8.png)  
GET       /employees/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5  

![img_9.png](picture/img_9.png)  
GET       /employees/male   #筛选出所有男性Employee  

![img_10.png](picture/img_10.png)  
POST      /employees    #增加一个employee  


![img_11.png](picture/img_11.png)  
PUT       /employees/1  #更新某个employee    

修改前  
![img_12.png](picture/img_12.png)  

修改  
![img_13.png](picture/img_13.png)  
修改后    
![img_14.png](picture/img_14.png)  
DELETE    /employees/1  #删除某个employee  
删除前  
![img_15.png](picture/img_15.png)  
删除操作  
![img_16.png](picture/img_16.png)  
删除后  
![img_17.png](picture/img_17.png)  
