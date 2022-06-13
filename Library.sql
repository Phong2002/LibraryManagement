drop database if exists LibraryManagement;
create database if not exists LibraryManagement;
use LibraryManagement;

create table `User`(
`libraryCard` varchar(10) primary key DEFAULT '0',
`fullname` varchar(100) not null,
`gender` enum ('Nam','Nữ','Khác') not null,
`dateOfBirth` date not null,
`address` text not null,
`numberPhone` varchar(13) unique not null,
`email`  varchar(100) unique not null,
`username` varchar(50) unique not null,
`password` varchar(50) not null,
`role` enum('librarian','borrower')
);

CREATE TABLE LIBRARYCARD
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

DELIMITER $$
CREATE TRIGGER tg_User_insert
BEFORE INSERT ON `User`
FOR EACH ROW
BEGIN
  INSERT INTO LIBRARYCARD VALUES (NULL);
  SET NEW.libraryCard=CONCAT('TV', LPAD(LAST_INSERT_ID(),2, '0'));
END$$
DELIMITER ;

insert into `User` (`fullname`,`gender`,`dateOfBirth`,`address`,`numberPhone`,`email`,`username`,`password`,`role`)
values ('Ngô Bá Khá','Nam','2002-03-05','Bắc Ninh','0369789654','khabanh@kute.email.com','khabanhkute','123456','librarian'),
		('Nguyễn Thành Long','Nam','1986-07-05','Hải Phòng','045216846','tiendepzai@email.com','tienkhongbip','123456','borrower'),
        ('Bùi Xuân Huấn','Nam','1983-06-05','Yên Bái','0369998751','huan9ngon@email.com','huanhoahongyb','123456','borrower'),
        ('Khánh Bầu Trời','Nam','1990-07-05','Hải Phòng','0158962365','khanh@sky-email.com','khanhsky123','123456','borrower'),
        ('Mai Thị Thúy','Nữ','1992-01-05','Bắc Giang','0632589245','maithuyok@gmail.com','maithuy9nam','123456','borrower'),
        ('Lò Vi Sóng','Nữ','1993-07-18','Lào Cai','063257416','lovisong@cooker.com','lovisong123','123456','borrower'),
        ('Lù Minh Phong','Nam','2002-05-03','Hà Giang','0369136828','phongkingvn@email.com','phongking02','123456','librarian'),
        ('Tô Kim Mạnh','Nam','2002-12-05','Điện Biên','0125639854','kimmanh@email.com','tokimmanh_tm','123456','librarian'),
        ('Phạm Đức Hòa','Nam','2002-03-05','Hà Nội','0689156514','duchoa2k2@kute.email.com','koduckbangavl','123456','librarian'),
        ('Dương Minh Tuyền','Nam','1986-07-05','Bắc Ninh','0156325986','tuyenmom@email.com','duongminhtuyenbn','123456','borrower');
        
        

create table `Book`(
`bookId`  varchar(10) primary key,
`name` varchar(200) not null,
`publisher` varchar(200) not null,
`yearOfPublisher` int not null,
`author` varchar(20) not null,
`subject` text not null,
`price` int not null,
`totalQuantity` int not null,
`summary` text not null
);


CREATE TABLE BOOKID
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

DELIMITER $$
CREATE TRIGGER tg_Book_insert
BEFORE INSERT ON `Book`
FOR EACH ROW
BEGIN
  INSERT INTO BOOKID VALUES (NULL);
  SET NEW.bookId = CONCAT('SA', LPAD(LAST_INSERT_ID(),2, '0'));
END$$
DELIMITER ;

insert into `Book`(`name`,`publisher`,`yearOfPublisher`,`author`,`subject`,`price`,`totalQuantity`,`summary`)
VALUES ('Lập trình cơ bản với C','NXB Công nghệ thông tin', 2016,'dont know','Sách Khoa học công nghệ – Kinh tế',150000,100, N'Với mong muốn chia sẻ kinh nghiệm học lập trình và các kỹ năng mà anh đã trải qua trong suốt quá trình học và làm việc với tư cách là người đi trước cũng như là một Developer Full Stack, nên anh đã quyết định xuất bản sách'),
('Code dạo ký sự – Lập trình viên đâu chỉ biết Code','NXB Dân trí', 2020,'Phạm Huy Hoàng','Sách Khoa học công nghệ – Kinh tế',150000,300, N'Đây là đầu sách công nghệ thông tin mà bạn nên đọc đầu tiên trước khi bạn tìm hiểu sâu hơn về lĩnh vực IT. Những kinh nghiệm và kỹ năng tác giả viết trong sách sẽ rất bổ ích cho bạn khi mới bắt đầu tìm hiểu về IT.'),
('Liệu IT Đã Hết Thời','NXB Trẻ', 2021,'Nicolas G.carr','Sách Khoa học công nghệ – Kinh tế',250000,250, N'đây cũng là một loại sách công nghệ thông tin nên đọc, nhưng nên đọc khi bạn đã bắt đầu có kinh nghiệm làm việc về IT, để bạn hiểu rõ hơn IT đã thay đổi cách hoạt động của các doanh nghiệp như thế nào.'),
('Nghệ thuật ẩn mình','NXB Công nghệ', 2018,'Kevin Mitnick','Sách Khoa học công nghệ – Kinh tế',289000,50, N'Dù bạn là một chuyên gia trong linh vực IT hay chỉ là người dùng internet bình thường thì những hoạt động trên mạng của bạn đều bị theo dõi dù muốn hay không. Vì vậy cuốn sách công nghệ thông tin – Nghệ thuật ẩn mình sẽ giúp bạn biết cách hạn chế sự rò rỉ những thông tin nhạy cảm của bản thân khi hoạt động trên mạng.'),
('Gián điệp mạng','NXB Tự do', 2015,'Cliff Stoll','Sách Khoa học công nghệ – Kinh tế',325000,400, N'Tôi nghĩ đây là một đầu sách mà bạn nên mua và đọc ngay, nội dung ly kỳ như một bộ phim hành động vậy, nó lôi cuốn người đọc theo hành trình của Cliff Stoll. Nếu bạn là một người thích phưu lưu cũng như sự kịch tính thì nên đọc nó. Đảm bảo bạn sẽ thấy những gì tôi nói là không sai.'),
('Chính Trị Luận','NXB Thế giới', 2018,'Aristotle','Sách Chính trị - pháp luật',169000,350, N'Tác phẩm nổi tiếng viết về các khái niệm mà từ đó định hình các quốc gia và chính phủ. Mặc dù, Aristotle cổ vũ mạnh mẽ cho chế độ nô lệ lạc hậu, quan điểm của ông về Hiến pháp và cách điều hành chính phủ lại rất kinh điển. Dù chỉ thảo luận về nhà nước và các định chế thời Hy Lạp cổ nhưng tác phẩm này của ông đã đặt nền tảng cho khoa học chính trị hiện đại'),
('Chính Trị – Khái Lược Những Tư Tưởng Lớn','NXB Thế giới', 2018,'DK','Sách Chính trị - pháp luật',298000,150, N'Có đúng chăng khi chúng ta lật đổ một nhà cai trị bất công? Liệu nền dân chủ có thực sự là hình thức chính quyền tốt nhất? Và chiến tranh có thể được biện minh hay không? Xuyên suốt chiều dài lịch sử, loài người đã tự hỏi mình những điều này cùng những câu hỏi lớn lao khác về cách thức tốt nhất để chúng ta cai trị chính mình và các tư tưởng gia vĩ đại đã đưa ra những lời giải đáp mà cho đến nay vẫn đang tiếp tục định hình thế giới.

Với văn phong dễ hiểu và sáng sủa, Chính trị – Khái Lược Những Tư Tưởng Lớn là tập hợp những bài viết ngắn gọn hàm súc giải thích rõ những điều khó hiểu, những sơ đồ từng bước giúp làm sáng tỏ những lí thuyết rối rắm và những hình ảnh minh họa dí dỏm giúp chúng ta ý thức rõ hơn về vai trò của mình trong cách thức tổ chức xã hội.

'),
('Bàn Về Khế Ước Xã Hội','NXB Thế giới', 2018,'J. Rousseau','Sách Chính trị - pháp luật',169000,350, N'Khế ước xã hội là tên gọi vắn tắt của bản luận văn lớn mà J. J. Rousseau đặt dưới một nhan đề khá dài: Bàn về khế ước xã hội hay là các nguyên tắc của quyền chính trị (Du Contrat social – ou principes du droit politique).'),
('Cộng Hòa','NXB Chính trị', 2012,'J. Rousseau','Sách Chính trị - pháp luật',189000,350, N'Cuốn sách được xem là cột mốc lớn của triết học phương Tây. Tác phẩm được trình bày dưới dạng đối thoại giữa Plato và những người khác. Mặc dù chủ đề chính là về một nhà nước lý tưởng nhưng nó cũng xoay quanh giáo dục, tâm lý, đạo đức và \
chính trị. Trong các đoạn chính của Cộng hòa, Plato sử dụng những huyền thoại để khám phá bản chất tự nhiên của thực tế, truyền đạt cái nhìn về sự tiên đoán của con người và vai trò của triết học trong việc thiết lập tự do. Ông tưởng tượng ra một cái hang mà những con người bị xiềng xích từ khi mới sinh ra làm bạn với cái bóng của mình và mang họ đến thực tế. Vai trò của triết học, cụ thể là những gì Plato gọi là biện chứng, là đưa con người ra khỏi cái bóng và hướng bản thân họ tới thực tế. Đây là bản chất của việc theo đuổi sự khôn ngoan mà không có nhà nước lý tưởng nào không làm. Độc giả hiện đại có thể đồng ý với mọi điều Plato nói, cũng như lập luận chặt chẽ, cái nhìn đầy chất thơ vẫn có sức mạnh trong việc kích thích và thách thức. Sức mạnh lâu dài này đã làm của Cộng hòa trở thành một trong những nền tảng của văn hóa phương Tây.');






create table `AwaitingApproval`(
`id` int auto_increment primary key,
`libraryCard` varchar(10) not null,
`bookId` varchar(10) not null,
`borrowedDay` int not null,
 CONSTRAINT FK_AwaitingApproval_User FOREIGN KEY (libraryCard)
        REFERENCES `User`(libraryCard)
        ON DELETE CASCADE ON UPDATE CASCADE,
 CONSTRAINT FK_AwaitingApproval_Book FOREIGN KEY (bookId)
        REFERENCES `Book`(bookId)
        ON DELETE CASCADE ON UPDATE CASCADE
);

create table `CallCard`(
`callCardId` varchar(10) primary key,
`libraryCard` varchar(10) not null,
`bookId` varchar(10) not null,
`borrowedDay` int not null,
`borrowedDate` date not null,

 CONSTRAINT FK_CallCard_User FOREIGN KEY (libraryCard)
        REFERENCES `User`(libraryCard)
        ON DELETE CASCADE ON UPDATE CASCADE,
 CONSTRAINT FK_CallCard_Book FOREIGN KEY (bookId)
        REFERENCES `Book`(bookId)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE CALLCARDID
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

DELIMITER $$
CREATE TRIGGER tg_CallCard_insert
BEFORE INSERT ON `CallCard`
FOR EACH ROW
BEGIN
  INSERT INTO CALLCARDID VALUES (NULL);
  SET NEW.callCardId = CONCAT('MS', LPAD(LAST_INSERT_ID(),2, '0'));
END$$
DELIMITER ;

create table ReturnBook(
`returnBookId` varchar(10) primary key,
`callCardId` varchar(10) not null,
`payDay` date not null,
`status` text not null,
`fines` int default 0, 
`note` text,
CONSTRAINT FK_ReturnBook_CallCard FOREIGN KEY (callCardId)
        REFERENCES `CallCard`(callCardId)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RETURNBOOKID
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

DELIMITER $$
CREATE TRIGGER tg_ReturnBook_insert
BEFORE INSERT ON `ReturnBook`
FOR EACH ROW
BEGIN
  INSERT INTO RETURNBOOKID VALUES (NULL);
  SET NEW.returnBookId = CONCAT('TS', LPAD(LAST_INSERT_ID(),2, '0'));
END$$
DELIMITER ;


