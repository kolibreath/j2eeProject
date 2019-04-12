# 后台接口需求

## 用户信息
> 用户注册

|Method|Header|URL|
|:----:|:----:|:----:|
POST|/api/auth/signup/|Content-Type:application/json|

URL:Params:None

POST Data
```
{
    "username":"string",
    "password":"string",
    "email":"string"
}
```

Return Data
```
{
    "token":"string"
}
```
Status code
```
200:ok
403:用户已注册
500:服务器错误
```
> 用户登录

|Method|Header|URL|
|:----:|:----:|:----:|
POST|/api/auth/signin/|Content-Type:application/json|

URL:Params:None

POST Data
```
{
    "username":"string",
    "password":"string",
    "email":"string"
}
```
Return Data

```
{
    "token":"string"
}
```
Status code
```
200:ok
403:用户已注册
500:服务器错误
```

> 用户获取自己的Profile

|Method|Header|URL|
|:----:|:----:|:----:|
GET|/api/user/|Content-Type:application/json,token(string)|

URL:Params:None

POST Data
```
None
```
Return Data
```
{
    "username":"",
    "avatar":"",
    "location":"",
    "description":""
    "points":int
}
```
Status code

```
200:ok
403:token 错误 
500:服务器错误
```

> 用户修改自己的Profile

|Method|Header|URL|
|:----:|:----:|:----:|
POST|/api/user/|Content-Type:application/json,token(string)|

URL:Params:None

POST Data
```
{
    "username":"",
    "avatar":"",
    "location":"",
    "description":""
    "points":int
}
```
Return Data
```
{
    "code":int
}
```
Status code
```
200:ok
403:token 错误
500:服务器错误
```


# 超话
> 用户根据某个关键字查询某个超话

|Method|Header|URL|
|:----:|:----:|:----:|
GET|/api/super_topic/|Content-Type:application/json|

URL:Params:超话名称 string
> 距离:https://base_url/api/super_topic/jennie
POST Data
```
None
```
Return Data
相关的超话内容（查询关键词）的列表
```
{
   list:
   [
       {
           "name":"",
           "subscriber_number":int,
           "avatar":"",
           "description":""
       }
   ]
}
```
Status code
```
200:ok
404:没有相关内容
500:服务器错误
```

> 用户根据关键字查询超话中的内容

|Method|Header|URL|
|:----:|:----:|:----:|
GET|/api/super_topic/{path_name}/|Content-Type:application/json|

URL:Params:想查询的超话内的内容 ：string
> eg : https://base_url/api/super_topic/jennie/rose
此时 path_name = jennie ,表示是在jennie超话， 查询内容是rose

POST Data
```
None
```
Return Data
```
{
    "contents":
    [
        "sender":"",
        "likes":int,
        "reviews":
        [
            {
                "reviewer":"",
                "avatar":""
            }
        ],
        "reposter":int,
        "content":"",
        "pictures":
        [
            "url":"string"
        ]
    ]
}
```

Status code
```
200:ok
404:没有相关内容
500:服务器错误
```

> 浏览超话内容


|Method|Header|URL|
|:----:|:----:|:----:|
GET|/api/super_topic/|Content-Type:application/json，token:string|

URL:Params:超话名称 string<br>
POST Data
```
None
```
Return Data
```
{
    "contents":
    [
        "sender":"",
        "likes":int,
        "reviews":
        [
            {
                "reviewer":"",
                "avatar":""
            }
        ],
        "reposter":int,
        "content":"",
        "pictures":
        [
            "url":"string"
        ]
    ]
}
```

Status code
```
200:ok
404:没有相关内容
500:服务器错误