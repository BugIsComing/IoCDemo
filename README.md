# IoCDemo
Model中对象依赖关系
        User
         |
  ----------------
  |              |
 Dao           Service
				 |
				Dao
为了验证（setter注入）循环依赖，特此添加Test类，其中Dao和Test互相依赖