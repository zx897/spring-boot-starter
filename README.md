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
![img.png](img.png)
GET       /companies/1  #获取某个具体company
![img_1.png](img_1.png)
GET       /companies/1/employees  #获取某个具体company下所有employee列表
![img_2.png](img_2.png)
GET       /companies/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
![img_3.png](img_3.png)
POST      /companies    #增加一个company
![img_4.png](img_4.png)
PUT       /companies/1  #更新某个company
![img_5.png](img_5.png)
DELETE    /companies/1  #删除某个company以及名下所有employees
![img_6.png](img_6.png)
#### employees API
GET       /employees    #获取employee列表
![img_7.png](img_7.png)
GET       /employees/1  #获取某个具体employee
![img_8.png](img_8.png)
GET       /employees/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
![img_9.png](img_9.png)
GET       /employees/male   #筛选出所有男性Employee
![img_10.png](img_10.png)
POST      /employees    #增加一个employee
![img_11.png](img_11.png)
PUT       /employees/1  #更新某个employee
修改前
![img_12.png](img_12.png)
修改
![img_13.png](img_13.png)
修改后![img_14.png](img_14.png)
DELETE    /employees/1  #删除某个employee
删除前
![img_15.png](img_15.png)
删除
![img_16.png](img_16.png)
删除后
![img_17.png](img_17.png)
