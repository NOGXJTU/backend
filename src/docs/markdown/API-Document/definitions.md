
<a name="definitions"></a>
## Definitions

<a name="activitydata"></a>
### ActivityData

|Name|Schema|
|---|---|
|**activityId**  <br>*optional*|string|
|**activityIdGroup**  <br>*optional*|< string > array|
|**beginTime**  <br>*optional*|string|
|**description**  <br>*optional*|string|
|**finished**  <br>*optional*|boolean|
|**isDuplicate**  <br>*optional*|boolean|
|**lasting**  <br>*optional*|string|
|**name**  <br>*optional*|string|
|**newOwnerId**  <br>*optional*|string|
|**ownerId**  <br>*optional*|string|
|**place**  <br>*optional*|string|
|**userGroup**  <br>*optional*|< string > array|
|**userId**  <br>*optional*|string|
|**userIdGroup**  <br>*optional*|< string > array|


<a name="applicationdata"></a>
### ApplicationData

|Name|Schema|
|---|---|
|**activityId**  <br>*optional*|string|
|**applicationId**  <br>*optional*|string|
|**description**  <br>*optional*|string|
|**status**  <br>*optional*|integer (int32)|
|**userId**  <br>*optional*|string|


<a name="emailvertifytokendata"></a>
### EmailVertifyTokenData

|Name|Schema|
|---|---|
|**email**  <br>*optional*|string|
|**isTokenCorrect**  <br>*optional*|boolean|
|**token**  <br>*optional*|string|


<a name="responseentity"></a>
### ResponseEntity

|Name|Schema|
|---|---|
|**body**  <br>*optional*|object|
|**statusCode**  <br>*optional*|enum (100, 101, 102, 103, 200, 201, 202, 203, 204, 205, 206, 207, 208, 226, 300, 301, 302, 303, 304, 305, 307, 308, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 426, 428, 429, 431, 451, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511)|
|**statusCodeValue**  <br>*optional*|integer (int32)|


<a name="userdata"></a>
### UserData

|Name|Schema|
|---|---|
|**activitesId**  <br>*optional*|< string > array|
|**avatar**  <br>*optional*|string|
|**description**  <br>*optional*|string|
|**duplicateFlag**  <br>*optional*|boolean|
|**email**  <br>*optional*|string|
|**isSuperUser**  <br>*optional*|boolean|
|**name**  <br>*optional*|string|
|**newPassword**  <br>*optional*|string|
|**passport**  <br>*optional*|string|
|**password**  <br>*optional*|string|
|**phone**  <br>*optional*|string|
|**qq**  <br>*optional*|string|
|**school**  <br>*optional*|string|
|**token**  <br>*optional*|string|
|**userId**  <br>*optional*|string|
|**userIdGroup**  <br>*optional*|< string > array|
|**username**  <br>*optional*|string|



