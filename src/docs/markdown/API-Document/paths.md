
<a name="paths"></a>
## Paths

<a name="getactivityusingpost"></a>
### 获取指定活动Id的活动信息
```
POST /activity
```


#### Description
传入活动Id(activityId),成功时返回状态200返回活动详细信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="addactivitymemberusingpost"></a>
### 添加成员
```
POST /activity/addMember
```


#### Description
传入活动Id(activityId)以及添加的成员的Id(userId),成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="getallactivityusingget"></a>
### 获取所有指定状态的活动的信息
```
GET /activity/all
```


#### Description
成功时返回状态200以及所有活动的部分信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**finished**  <br>*required*|finished|boolean|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="deleteactivityusingpost"></a>
### 删除指定活动
```
POST /activity/delete
```


#### Description
传入活动Id(activityId),成功时返回状态200,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="isduplicateactivityusingpost"></a>
### 活动是否重复
```
POST /activity/duplicate
```


#### Description
传入活动名称(name)活动起始时间(beginTime),成功时返回状态200,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="generateactivityusingpost"></a>
### 生成活动
```
POST /activity/generate
```


#### Description
传入除了活动成员Id和活动状态之外的所有其他参数,成功时返回状态200,并且返回活动信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="modifyactivityusingpost"></a>
### 修改指定活动信息
```
POST /activity/modify
```


#### Description
传入活动Id(activityId)以及活动的信息,成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="quitactivityusingpost"></a>
### 主动退出活动
```
POST /activity/quit
```


#### Description
传入活动Id(activityId),成功时返回状态200,并返回修改后活动的信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="removeactivitymemberusingpost"></a>
### 移除成员
```
POST /activity/removeMember
```


#### Description
传入活动Id(activityId),移除的成员的Id(userId),成功时返回状态200,并返回修改后活动的信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="removeactivitymembergroupusingpost"></a>
### 批量移除成员
```
POST /activity/removeMember/Group
```


#### Description
传入活动Id(activityId)以及移除成员列表(userIdGroup),成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="modifystatusactivityusingpost"></a>
### 修改指定活动状态
```
POST /activity/status/modify
```


#### Description
传入活动Id(activityId),成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="transferactivityownerusingpost"></a>
### 转让组长权限
```
POST /activity/transfer
```


#### Description
传入活动Id(activityId),新的组长的Id(newOwnerId),成功时返回状态200,并返回修改后活动的信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ActivityData](#activitydata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* activity-controller


<a name="applicationregisterusingpost"></a>
### 发送活动加入申请
```
POST /application
```


#### Description
传入活动Id(activityId),申请理由(description),成功时返回状态200,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ApplicationData](#applicationdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* application-controller


<a name="applicationconfirmusingpost"></a>
### 同意活动申请
```
POST /application/confirm
```


#### Description
传入申请Id(applicationId),成功时返回状态200,返回当前申请的信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ApplicationData](#applicationdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* application-controller


<a name="getallapplicationbyactivityidusingpost"></a>
### 获取指定活动的所有申请
```
POST /application/getAllApp
```


#### Description
传入活动Id(activityId),成功时返回状态200并且返回所有该活动信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ApplicationData](#applicationdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* application-controller


<a name="getallapplicationbyactivityidandstatususingpost"></a>
### 获取指定活动的所有指定状态的申请
```
POST /application/getAllAppWithStatus
```


#### Description
传入活动Id(activityId),申请状态(status),成功时返回状态200,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ApplicationData](#applicationdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* application-controller


<a name="applicationrefuseusingpost"></a>
### 拒绝活动申请
```
POST /application/refuse
```


#### Description
传入申请Id(applicationId),成功时返回状态200,返回当前申请的信息,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**data**  <br>*required*|data|[ApplicationData](#applicationdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* application-controller


<a name="sendtokenusingpost"></a>
### 发送邮箱验证码
```
POST /email/sendToken
```


#### Description
传入邮箱(email),成功时返回状态200,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**data**  <br>*required*|data|[EmailVertifyTokenData](#emailvertifytokendata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* email-vertify-token-controller


<a name="tokenvertifyusingpost"></a>
### 验证邮箱与验证码是否正确
```
POST /email/tokenVertify
```


#### Description
传入邮箱(email),验证码(token),成功时返回状态200,失败时返回状态以及错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**data**  <br>*required*|data|[EmailVertifyTokenData](#emailvertifytokendata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* email-vertify-token-controller


<a name="responseisemailduplicateusingpost"></a>
### 邮箱是否重复
```
POST /user/duplicate/email
```


#### Description
传入邮箱(email).返回状态200,不重复时候返回vertifyStatus为true,重复的时候返回vertifyStatus为false


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="responseisphoneduplicateusingpost"></a>
### 手机号是否重复
```
POST /user/duplicate/phone
```


#### Description
传入手机号(phone).返回状态200,不重复时候返回vertifyStatus为true,重复的时候返回vertifyStatus为false


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="responseisusernameduplicateusingpost"></a>
### 用户名是否重复
```
POST /user/duplicate/username
```


#### Description
传入用户名(username).返回状态200,不重复时候返回vertifyStatus为true,重复的时候返回vertifyStatus为false


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="forgetuserpasswordmodifyusingpost"></a>
### 忘记密码流程的密码修改
```
POST /user/forget/passwordModify
```


#### Description
传入邮箱(email),密码(password),验证码(token).成功时候返回状态200,返回成功与否信息,失败的时候返回状态和错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* email-vertify-token-controller


<a name="userpasswordmodifyusingpost"></a>
### 验证已经登录用户的密码(用来修改用户密码)
```
POST /user/passwordModify
```


#### Description
传入用户通行证(passport)和用户密码(password)以及新的密码.成功时候返回状态200,不会返回用户信息(更不会返回新的密码),失败返回状态


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="userregisterusingpost"></a>
### 用户注册
```
POST /user/register
```


#### Description
传入用户的用户名(username),密码(password),真实姓名(name),邮箱(email),手机号(phone),qq号(qq),学校(school).成功时候返回状态200同时返回该用户的所有信息,失败时候返回状态并且附有错误信息. 因为通过接口验证过各种信息的错误,所以缺失参数和参数重复默认为恶意访问,将返回参数缺失错误


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="usersigninusingpost"></a>
### 用户登陆
```
POST /user/signIn
```


#### Description
传入用户的通行证(passport)和密码(password).成功时返回状态200和用户信息,失败时返回状态和错误信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="usersignoutusingput"></a>
### 用户登出
```
PUT /user/signOut
```


#### Description
无参数传入,当前用户登出


#### Parameters

|Type|Name|Schema|
|---|---|---|
|**Query**|**creationTime**  <br>*optional*|integer (int64)|
|**Query**|**id**  <br>*optional*|string|
|**Query**|**lastAccessedTime**  <br>*optional*|integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*|integer (int32)|
|**Query**|**new**  <br>*optional*|boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*|ref|
|**Query**|**servletContext.contextPath**  <br>*optional*|string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*|string|
|**Query**|**servletContext.majorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*|string|
|**Query**|**servletContext.servletContextName**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*|boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*|boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*|string|
|**Query**|**valueNames**  <br>*optional*|< string > array(multi)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="responseissuperuserusingpost"></a>
### 是否为超级管理员
```
POST /user/usSuperUser
```


#### Description
无需传递任何参数.成功时返回状态200,返回是否为超级管理员Flag


#### Parameters

|Type|Name|Schema|
|---|---|---|
|**Query**|**creationTime**  <br>*optional*|integer (int64)|
|**Query**|**id**  <br>*optional*|string|
|**Query**|**lastAccessedTime**  <br>*optional*|integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*|integer (int32)|
|**Query**|**new**  <br>*optional*|boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*|ref|
|**Query**|**servletContext.contextPath**  <br>*optional*|string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*|string|
|**Query**|**servletContext.majorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*|string|
|**Query**|**servletContext.servletContextName**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*|boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*|boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*|string|
|**Query**|**valueNames**  <br>*optional*|< string > array(multi)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="getuserinfousingpost"></a>
### 获取指定Id的用户的信息
```
POST /user/userInfo
```


#### Description
传入指定的用户的用户Id(userId).成功时返回状态200,返回指定的用户的信息


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="getcurrentuserinfousingget"></a>
### 获取当前登陆的用户信息
```
GET /user/userInfo
```


#### Description
无参数传入.成功时返回状态200,返回当前登陆的用户信息


#### Parameters

|Type|Name|Schema|
|---|---|---|
|**Query**|**creationTime**  <br>*optional*|integer (int64)|
|**Query**|**id**  <br>*optional*|string|
|**Query**|**lastAccessedTime**  <br>*optional*|integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*|integer (int32)|
|**Query**|**new**  <br>*optional*|boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*|ref|
|**Query**|**servletContext.contextPath**  <br>*optional*|string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*|string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*|string|
|**Query**|**servletContext.majorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*|string|
|**Query**|**servletContext.servletContextName**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*|boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*|integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*|string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*|boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*|string|
|**Query**|**valueNames**  <br>*optional*|< string > array(multi)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="userinfomodifyusingpost"></a>
### 修改用户信息
```
POST /user/userInfo/Modify
```


#### Description
传入允许修改的参数(头像avatar,个性宣言description,QQ).成功时返回状态200,返回新的用户信息,失败的时候


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**creationTime**  <br>*optional*||integer (int64)|
|**Query**|**id**  <br>*optional*||string|
|**Query**|**lastAccessedTime**  <br>*optional*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*optional*||integer (int32)|
|**Query**|**new**  <br>*optional*||boolean|
|**Query**|**servletContext.classLoader**  <br>*optional*||ref|
|**Query**|**servletContext.contextPath**  <br>*optional*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*optional*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*optional*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*optional*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*optional*||string|
|**Query**|**servletContext.majorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.serverInfo**  <br>*optional*||string|
|**Query**|**servletContext.servletContextName**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*optional*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*optional*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*optional*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*optional*||boolean|
|**Query**|**servletContext.virtualServerName**  <br>*optional*||string|
|**Query**|**valueNames**  <br>*optional*||< string > array(multi)|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller


<a name="geruserinfogroupusingpost"></a>
### 批量获取用户信息
```
POST /user/userInfoGroup
```


#### Description
传入用户Id列表(userIdGroup).成功时候返回状态200,返回用户信息列表


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userData**  <br>*required*|userData|[UserData](#userdata)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* user-controller



