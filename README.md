# Document  AutoTouchPro
App yêu cầu root máy để hoạt động được các tính năng: backup, restore data của app khác, sock - proxy 
# Cách cài đặt
- Tải apk AutoTouchPro mới nhất, cài đặt vào điện thoại.
- Mở app lên, cho phép các quyền đọc ghi bộ nhớ, quyền ghi màn hình, quyền VPN,...
- App sẽ tự mở phần `Setting` -> sau đó tìm phần `Accessibility` (Trợ năng) -> `AutoTouchPro` -> `On` 
- Xuất hiện 2 nút `Run` và `Stop` trên màn hình là app đã chạy thành công

# Cách chạy scrip
1. Soạn script theo kịch bản như mô tả các lệnh bên dưới, copy file đã soạn `script_test.txt` vào điện thoại theo đường dẫn `/sdcard/AutoTouchPro/`
2. Chạy lệnh qua adb
- cắm đt vào máy tính, đảm bảo adb được cài trên máy tính
- chạy lệnh trong file `script_test.txt` thực hiện lệnh adb trên máy tính :
```shell script
adb shell am start-foreground-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es "file" "script_test.txt"
```
không chạy được trên một số điện thoại thì bỏ chữ *foreground-* đi
```shell script
adb shell am start-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es "file" "script_test.txt"
```

**Chạy scrip đơn lẻ, không cần soạn, hỗ trợ lệnh đơn , ngắn không phức tạp, không nhiều dấu nháy lồng nhau**
ví dụ
```shell script
adb shell am start-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es "command" "openaapp -p com.facebook.katana"
adb shell am start-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es "command" "home"
adb shell am start-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es "command" "back"

```

# *________________CÁCH SOẠN SCRIPT__________________

# Cấu trúc script gồm 2 phần
1. Khai báo
2. Body script, chạy theo kiểu gần giống `command line`
## Cấu trúc lệnh khai báo 
1. Các biến phải nằm trong cặp thẻ `define` và `endDefine`
2. Khai báo biến, các biến bắt đầu bằng `$``name` = `giá trị`
3. Các biến sẽ được nạp vào bộ nhớ để sử dụng cho body script
4. Các biến có thể là lệnh rút gọn cho lệnh dài hoặc biến
5. Chú ý các dấu nháy `""`, dấu `''`, và `{}` nếu trong 1 lệnh có nhiều param lồng nhau, độ ưu tiên theo thứ tự `{}` ở ngoài cùng -> `''` -> `""` : nếu không theo độ ưu tiên này thì script sẽ lỗi, ví dụ đúng
```shell script
 if -getVar "donevar" -equal null -else {wait -text 'random -text "Done,Cancel"' -exactly true -timeout "random -from 10000 -to 30000" -thenClick true}
```
6. Ví dụ
```shell script
define
	//screen 420dp -> 425dp
	$pk = com.amazon.mShop.android.shopping
	
	//thời gian sleep giữa các thao tác
	$sleep = sleep -t "random -from 500 -to 900"
	
	//toạ độ cuộn và random thời gian cuộn
	$scrollUp = swipe -orient up -time "random -from 250 to 1500" //time càng bé scroll càng nhanh
	$scrollDown = swipe -orient down -time 'random -from 250 to 1500' //time càng bé scroll càng nhanh
	
	//pick random 1 từ khoá trong số này và chạy
	$randomTukhoa = random -text "bluetooth,headphone,mouse,keyboard,adapter,paper" -var "tukhoa"
	$getTuKhoa = 'getVar  -var "tukhoa"'
	
	//số lần xem sản phẩm trong 1 từ khoá
	$count_product = 'random -from 1 -to 3'
	//số lần bấm add to card hoặc ADD TO LIST
	$random_addtocard = 'random -from 1 -to 2'
	//số lần vuốt ảnh trong sản phẩm
	$random_swipeimage = 'random -from 1 -to 4'
	//số lần vuốt you might also like
	$random_you_maight_also_like = 'random -from 1 -to 4'
	//số lần xem video trong sản phẩm
	$random_video = 'random -from 1 -to 2'
	//số thời gian xem 1 video
	$random_video_time = sleep -t "random -from 10000 -to 30000"
	//số lần vuốt compare with similar item
	$random_compare_with_similar = 'random -from 1 -to 4'
	//số lần vuốt more to consider
	$random_more_to_consider = 'random -from 1 -to 4'
	//số lần vuốt customer_who_bought...
	$random_customer_who_bought = 'random -from 1 -to 4'
	//số lần bấm helpful
	$random_helpful = 'random -from 1 -to 3'
	
	$swipeLeft = 'swipe -orient left -time "random -from 300 -to 700"'
	$enter = click -x 980 -y 1850
	$search = click -x 600 -y 250
	$swipeBellow = swipe -startX 700 -startY 1350 -endX "random -from 50 -to 200" -endY  "random -from 1350 -to 1550" -time "random -from 300 -to 1000"
	$clickBellow = click -x "random -from 100 -to 400" -y "random -from 1200 -to 1450"
	
	
	//
	$timeout_to_stop = -timeout 60000 -else 'stop -text "----------TASK-DONE---------"'
	$swipeRandom = swipe -orient 'random -text "up,down"' -time 'random -from 200 -to 800'
	
endDefine
```
## Cấu trúc lệnh Body
1. Nếu muốn Chạy đa luồng (Tất cả các lệnh cùng chạy 1 lúc), không được để các lệnh/khối lệnh trong khối `sequent` và `endSequent`
- Hai vòng lặp chạy cùng lúc trong vòng 500s , cứ xuất hiện `Ok` là click và cứ xuất hiện `NONE OF THE ABOVE` là bấm, hết 500s thì dừng
```shell script
loop -timeout 500000
	wait -text "Ok" -exactly true -timeout 300000 -thenclick true
endloop

loop -timeout 500000
	if -text "NONE OF THE ABOVE" -thenClick true -timeout 300000
endloop
```
2. Nếu muốn chạy tuần tự
- thì cần đặt các lệnh vào thẻ `sequent` và `endSequent` . 2 thẻ này cũng giống 1 lệnh chứa các lệnh con, nếu muốn 2 lệnh tuần tự chạy song song thì tạo 2 khối `sequent` và `endSequent` như phần 1
- ví dụ khai báo và chạy tuần tự
```shell script
define
	$pk = com.amazon.mShop.android.shopping
	
	$sleep = sleep -t "random -from 1000 -to 4000"
	
	//---------------login-------------
	$user = love1608815756838@mailshu.com
	$pass = 123456
	
	//----nếu check point-----
	$email = pdhainam@gmail.com
	$emailPass = Nolove1@123

	$enter = click -x 980 -y 1850
endDefine

sequence
	log -text "----------TASK-START---------"
	//----------------mở app và login-----------
	openapp -p "$pk"
	sleep -t 5000
	setInfo -p "$pk" -field "current_status" -value "opening app"
	wait -text 'Done' -exactly true -timeout 30000 -thenInput '' -thenClick true -then '' -else ''
	$sleep
	wait -text 'Already a customer? Sign in' -exactly true -timeout 10000 -thenInput '' -thenClick true
	$sleep
	if -text 'Sign in for the best experience' -exactly true -timeout 10000 -then 'wait -text "Sign in" -exactly true -timeout 10000 -thenClick true'
	$sleep
	$sleep
	$sleep
	wait -text 'Email (phone for mobile accounts)' -timeout 10000 -thenInput '$user' -slowinput true
	$sleep
	$sleep
	wait -text 'Continue' -exactly true -timeout 10000 -thenInput '' -thenClick true
	$sleep
	$sleep
	$sleep
	wait -editText 'Amazon password' -timeout 10000 -thenInput '$pass' -slowinput true
	$sleep
	$sleep
	$sleep
	wait -buttonText 'Sign-In' -exactly true -timeout 10000 -thenClick true -then 'setInfo -p "$pk" -field "current_status" -value "Logged"'
	$sleep
	$sleep
	$sleep
	if -buttonText 'Sign-In' -exactly true -timeout 10000 -else 'setInfo -p "$pk" -field "loged_user" -value "$user"'
	if -buttonText 'Sign-In' -exactly true -timeout 10000 -else 'setInfo -p "$pk" -field "loged_password" -value "$pass"'
	if -text 'To continue, approve the notification sent to' -timeout 10000 -then 'setInfo -p "$pk" -field "current_status" -value "Check point"'
	home
	log -text "----------TASK-DONE---------"
endSequence
```





# _____________________________CÁC LỆNH ĐƠN CƠ BẢN_______________________________
(Không phân biệt chữ hoa, chữ thường)
- `sequent` và `endSequent` : khối lệnh để chạy các lệnh khác một cách tuần tự

- `home` không có param: bấm nút home vật lý trên điện thoại
- `back` Nút vật lý trở về
- `recent` Nút vật lý mở recent app
- `opennotification` mở thanh status bar
- `stop` hoặc `stopAll` dừng tất cả các lệnh đang chạy

- `var` Lưu giá trị vào 1 biến trong lúc chạy

	ví dụ 
```shell script
    var -name "code" -value "100"
    var -name "code2" -value "random -from 11 -to 100"
chụp ảnh màn hình theo tọa độ rồi lưu ảnh vào biến
    var -name "image" -value 'getImage -startX 100 -startY 150 -endX 500 -endY 550';

```


- `getvar` Lấy giá trị biến trong lúc chạy

	ví dụ 
```shell script
wait -text 'What are you looking for?' -exactly true -timeout 10000 -thenInput 'getVar  -var "code"' -slowinput true
```

- `random` sinh ngẫu nhiên 1 số từ x đến y

	param `-from` x `-to` y
	ví dụ
```shell script
random -from 1000 -to 4000
```

- `click` click vào 1 tọa độ trên màn hình hoặc `clickcenter` để click vào giữa màn hình

	param `-x` , `-y`;   `-plusX "-150" -plusY "-150"` tọa độ cộng thêm so với x,y
	ví dụ
```shell script
click -x 500 -y 100
//
click -x "random -from 100 -to 400" -y 100
```

- `doubleclick` tương tự `click`
- `longclick` tương tự `click`



- `openapp` mở 1 app theo tên package

	param `-p` : tên package
	ví dụ
```shell script
openapp -p "com.amazon.mShop.android.shopping"
```

- `opendetailapp` mở cài đặt chi tiết app theo tên package

	param `-p` : tên package
	ví dụ
```shell script
opendetailapp -p "com.amazon.mShop.android.shopping"
```


- `sleep` delay trong khoảng thời gian millisecond, **chạy trong khối  `sequent` và `endSequent` hoặc `loop` mới có tác dụng**

	param: `-t` : thời gian millisecond
	ví dụ
```shell script
//sleep 1 giây
sleep -t 1000
//hoặc
sleep -t "random -from 1000 -to 4000"
```

- `swipe` cử chỉ vuốt trên màn hình

	param `-orient` hướng vuốt là 1 trong các giá trị  `up` `down` `left` `right` ---- hoặc param `-startx 100 -starty 100 -endx 500 -endy 500` từ tọa độ (x1,y1) tới (x2,y2)
	param `-t` thời gian millisecond swipe: thời gian từ nhỏ nhất từ `100` , thời gian càng bé thì tốc độ vuốt càng nhanh 
	ví dụ
```shell script
swipe -orient up -t 200
//hoặc
swipe -startx 100 -starty 100 -endx 500 -endy 500 -t 1000
```


# _____________________________CÁC LỆNH NÂNG CAO_______________________________

- `loop` và `endLoop` là 1 khối lệnh lặp

	param `-t` : thời gian lặp hoặc `-i` số lần lặp
	ví dụ
```shell script
//trong vòng 4 giây chạy 2 lệnh tuần tự sleep 4giay rồi back
loop -t 4000
  sleep -t 1000
  back
endLoop


//hoặc chạy 30 lần
loop -i 30
  sleep -t 1000
  back
endLoop
```

- `if` hoặc `wait` Lệnh chờ 1 điều kiện xuất hiện trên màn hình như chờ 1 edittext sau đó làm gì, hoặc kiểm tra biến sau đó làm hành động

	param: `-text "Facebook"` Chờ 1 text xuất hiện trên màn hình, `-edittext "Tên"` check text ô nhập liệu, `-id "id_view"` trong trường hợp text không có, cần debug để tìm ra id của view đó 
	param: `-exactly true` check text là đúng thay vì contain text
	param: `-timeout` chờ trong khoảng thời gian,
	param: `-then` Chạy lệnh nào đó
	param: `-thenInput "text"` Nhập text vào ô đã tìm thấy
	param: `-slowinput true` Nhập text vào ô đã tìm thấy theo kiểu chậm từng chữ một
	param: `-thenClick true` sau đó click vào ô đó
	param: `-thenDoubleClick true` sau đó click vào ô đó
	param: `-thenLongClick true` sau đó long click vào ô đó
	param: `-thenScrollto true` sau đó cuộn tới ô đó
	param: `-else` nếu không thì chạy lệnh nào đó
	param: `-pos 0` trong trường hợp trên màn hình có nhiều text đó thì lấy vị trí 0

	ví dụ *đọc thêm các scrip mẫu bên thư mục trên để hiểu thêm*
```shell script
   wait -text "test text" -timeout 5000 -thenclick true
   wait -text "test text" -timeout 5000 -thenlongclick true -thenswipe "up" -theninput "1234" -var "vartest"
   wait -text "test text" -timeout 5000 -thenswipe "up" -theninput "1234" -var "vartest"
   wait -text "test text" -timeout 5000 -theninput "1234"

//lưu 1234 vào biến vartest
   wait -text "test text" -timeout 5000 -theninput "1234" -var "vartest"
//check biến vartest
   if -getVar "vartest" -equal null -then '' -else ''
   if -getVar "vartest" -contain "1" -then '' -else ''
   wait -edittext "" -pos 0 -timeout 50000 -theninput "randomPhone" //tìm tất cả edittext có thể nhập được, sau đó điền random sđt vào vị trí 0
   wait -buttontext "" -pos 0 -timeout 50000 -thenClick true -thenclick true -plusX "-150" -plusY "-150"
   wait -id "numberpicker_input" -pos 0 -timeout 5000 -theninput "Feb"
   wait -id "numberpicker_input" -pos 1 -timeout 5000 -theninput "10"
   wait -id "numberpicker_input" -pos 2 -timeout 5000 -theninput "1997"

    wait -text 'Done' -exactly true -timeout 10000 -thenInput '' -thenClick true -then '' -else '' -var "donevar"
	if -getVar "donevar" -equal null -else {wait -text 'Skip sign in' -exactly true -timeout 10000 -thenClick true}

//nếu màn hình hiện chữ `Sign in for the best experience` thì tìm chữ `Sign in` để click
	if -text 'Sign in for the best experience' -exactly true -timeout 10000 -then 'wait -text "Sign in" -exactly true -timeout 10000 -thenClick true'

```

- `getotp` dùng để lấy OTP từ mã 2FA

	param `-key` : mã 2FA
	ví dụ
```shell script
wait -text "login code" -timeout 300000 -theninput "getOTP -key P6NTOT7IVUCVQEAKGA2CWWO3SD5HXJ45"
```

- `openfacebookpost` hỗ trợ mở 1 post riêng của facebook

	ví dụ
```shell script
OpenFacebookPost -p com.facebook.katana -id 949449446412651
```



- `opendeeplink` hỗ trợ mở 1 deeplink theo app

	ví dụ
```shell script
//mở app tiktok với link video, search deeplink trên google để hiểu hơn
OpenDeepLink -p "com.ss.android.ugc.trill" -url "https://vt.tiktok.com/ZSXCh2Hb"
```

- `changeProxy` Đổi proxy trừ app mình , *lưu ý proxy không có authent*, còn proxy có authen như sock5,... thì phức tạp hơn, cần root máy và cài thêm 1 số thứ
- `stopProxy` Đổi proxy trừ app mình
	ví dụ 
```shell script
   changeProxy -ip "123.235.123.1:8080"
   stopProxy
```



- `replace`

	param `-replace` là regex hoặc text bình thường
	ví dụ 
```shell script
//replace giá trị trong biến phone và lưu lại vào biến phone
replace -text 'getVar -name "phone"' -replace "^84" -to "0" -var "phone"
```


- `get` lấy giá trị 1 trường của json từ 1 link

	param `-url`, `-field`, `-regex` để lọc text trong field , `-regexpos` vị trí khớp regex, `startAt` lấy kết quả từ vị trí sau khi lọc regex, `endAt` vị trí cuối cùng lấy vào kết quả
	ví dụ
```json
{"menu": {
    "header": "SVG Viewer",
    "items": [
        {"id": "Open"},
        {"id": "OpenNew", "label": "Open New"},
        {"id": "OpenNew", "label": "Open New"}
    ]
}}
```
	thì lấy các trường kiểu, *đọc các script mẫu phần auto amazon để hiểu*
```shell script
//define code rút gọn
define
//lấy và lưu vào biến `id_phone`
	$requestPhone = get -url "https://api.rentcode.net/api/v2/order/request?apiKey=maaH76Urmo05DH2GLDAftyaPLyiLLlomhUztTcgmSqkv&serviceProviderId=22" -field "id" -var "id_phone"
	$getOTP = get -url "getVar -name get_link_otp" -field message -regex "[0-9]{6}" -regexPos 1 -var "otp"
	$gen_link_request = replace -text "https://api.rentcode.net/api/v2/order/_id_/check?apiKey=maaH76Urmo05DH2GLDAftyaPLyiLLlomhUztTcgmSqkv" -replace "_id_" -to "getVar -name id_phone" -var "get_link_otp"
endDefine
// điền vào edit text
wait -text "write to comment" -timeout 20000 -theninput "get -url https://api.mocki.io/v1/b043df5a -field menu.items[2].id"
```



- `reademail` đọc giá trị trong mail gửi về với regex tương tự

	các param regex giống lệnh `get` bên trên
	ví dụ
```shell script
define
    //đọc 15 mail mới nhất, tìm tất cả số có 5 chữ số và lấy vị trí số thứ 1
	$email = artemgmhed@hotmail.com
	$emailPass = ejp7llnYtul
    $code = readMail -user "artemgmhed@hotmail.com" -pass "ejp7llnYtul"  -contain "mã xác nhận" -limit 15 -regex "[0-9]{5}" -regexPos 1
endDefine

wait -text "code" -timeout 30000 -theninput '$code'

```


- `readcaptcha` giải captche từ trang https://api.anti-captcha.com

	ví dụ
```shell script
  var -name "image" -value 'getImage -startX 100 -startY 150 -endX 500 -endY 550';
  wait -editText "captcha" -timeout 10000 -pos 0 -thenInput 'readcaptcha -key "0407590008409e6e69f340089f6a40355" -image "getVar -var image"'
```

#Các lệnh yêu cầu root
- Backup data của 1 app theo package
```shell script
//backup vào thư mục /sdcard/AutoTouchPro/backup/com.facebook.katana/test.bk
// nó sẽ tạo ra 2 file test.bk và test.bk.log để khi restore đọc lại thông tin
backupapp -p "com.facebook.katana" -to 'AutoTouchPro/backup' -name 'test.bk';
```
- Restore data của 1 app theo package
```shell script
//restore file trong /sdcard/AutoTouchPro/backup/com.facebook.katana/test.bk
sequent
	home
	restoreapp -p "com.facebook.katana" -from 'AutoTouchPro/backup' -name 'test.bk'
	openapp -p "com.facebook.katana"
endsequent
```
