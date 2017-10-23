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


待优化地方
1、如果类依赖的是数组类型的对象，无法注入