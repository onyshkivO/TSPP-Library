package com.onyshkiv.DAO;

public abstract class SQLQuery {
    public static class PublicationQuery {

        public static final String SELECT_ALL_PUBLICATIONS = "SELECT * FROM publication ORDER BY name";
        public static final String SELECT_PUBLICATION_BY_ID = "SELECT * FROM publication WHERE publication_id = ? ";
        public static final String INSERT_PUBLICATION = "INSERT INTO publication VALUES (DEFAULT,?)";
        public static final String UPDATE_PUBLICATION = "UPDATE publication SET name =? WHERE publication_id=?";
        public static final String DELETE_PUBLICATION = "DELETE FROM publication WHERE publication_id = ?";
        public static final String IS_CONTAINS_PUBLICATION = "SELECT Count(1) FROM publication WHERE publication_id = ?";
    }

    public static class AuthorQuery {
        public static final String SELECT_ALL_AUTHORS = "SELECT * FROM authors";
        public static final String SELECT_AUTHOR_BY_ID = "SELECT * FROM authors WHERE authors_id=?";
        public static final String INSERT_AUTHOR = "INSERT INTO authors VALUES(default,?)";
        public static final String UPDATE_AUTHOR = "UPDATE authors SET name=? \n" +
                "WHERE authors_id = ?";
        public static final String DELETE_AUTHOR = "DELETE FROM authors WHERE authors_id = ?";

        public static final String M2M_BOOKS_AUTHORS = "SELECT a_id\n" +
                "FROM  book_has_authors\n" +
                "WHERE b_isbn=?";
        public static final String M2M_INSERT_BOOK_AND_AUTHOR = "INSERT INTO book_has_authors VALUES (?,?)";
        public static final String M2M_REMOVE_BOOK_AND_AUTHOR = "DELETE FROM book_has_authors WHERE b_isbn=?";
    }

    public static class BookQuery {
        public static final String FIND_ALL_BOOKS = "SELECT isbn, name,date_of_publication, publication_id, quantity, details\n" +
                    "FROM book WHERE is_active = true";


        public static final String FIND_AVAILABLE_BOOKS_BY_SOME_OPTION ="SELECT b.isbn, b.name, b.date_of_publication, b.publication_id, b.quantity, details, GROUP_CONCAT(a.name ORDER BY a.name ASC SEPARATOR '') AS authors\n" +
                "        FROM book b\n" +
                "        left JOIN book_has_authors bha ON b.isbn = bha.b_isbn\n" +
                "        left JOIN authors a ON bha.a_id = a.authors_id\n" +
                "left join  publication p using(publication_id)\n" +
                "        where quantity>0 AND is_active = true AND %s.name like ?\n" +
                "        GROUP BY b.isbn\n" +
                "        order by %s %s limit ? offset ?";

        public static final String FIND_BOOKS_BY_SOME_OPTION ="SELECT b.isbn, b.name, b.date_of_publication, b.publication_id, b.quantity,  details, GROUP_CONCAT(a.name ORDER BY a.name ASC SEPARATOR '') AS authors\n" +
        "        FROM book b\n" +
        "        left JOIN book_has_authors bha ON b.isbn = bha.b_isbn\n" +
        "        left JOIN authors a ON bha.a_id = a.authors_id\n" +
        "left join  publication p using(publication_id)\n" +
        "        where is_active = true AND %s.name like ?\n" +
        "        GROUP BY b.isbn\n" +
        "        order by %s %s limit ? offset ?";


        public static final String FIND_NUMBER_OF_AVAILABLE_BOOKS_BY_SOME_OPTION ="SELECT COUNT(*) FROM (SELECT distinct isbn, b.name,date_of_publication, publication_id, quantity, details\n" +
                "from book b\n" +
                "left join  book_has_authors  on b_isbn = isbn\n" +
                "left join  authors a on a_id = authors_id\n" +
                "left join  publication p using(publication_id)\n" +
                "where quantity>0 AND is_active = true AND %s.name like ?) as number";
        public static final String FIND_NUMBER_OF_BOOKS_BY_SOME_OPTION ="SELECT COUNT(*) FROM (SELECT distinct isbn, b.name,date_of_publication, publication_id, quantity, details\n" +
                "from book b\n" +
                "left join  book_has_authors  on b_isbn = isbn\n" +
                "left join  authors a on a_id = authors_id\n" +
                "left join  publication p using(publication_id)\n" +
                "where is_active = true AND %s.name like ?) as numbers";




        public static final String FIND_AVAILABLE_BOOKS_BY_NAME ="SELECT distinct isbn, b.name,date_of_publication, publication_id, quantity, details\n" +
                "from book b\n" +
                "left join  book_has_authors  on b_isbn = isbn\n" +
                "left join  authors a on a_id = authors_id\n" +
                "left join  publication p using(publication_id)\n" +
                "where quantity>0 AND is_active = true AND  b.name like ? order by ";

        public static final String FIND_AVAILABLE_BOOKS_BY_AUTHOR_NAME ="SELECT distinct isbn, b.name,date_of_publication, publication_id, quantity, details\n" +
                "from book b\n" +
                "left join  book_has_authors  on b_isbn = isbn\n" +
                "left join  authors a on a_id = authors_id\n" +
                "left join  publication p using(publication_id)\n" +
                "where quantity>0 AND is_active = true AND a.name like ? order by ";




        public static final String FIND_BOOK_BY_ISBN = "SELECT isbn, name,date_of_publication, publication_id, quantity, details\n" +
                "FROM book\n" +
                "WHERE isbn =? AND is_active = true";
        public static final String FIND_BOOK_BY_ISBN_ALL = "SELECT isbn, name,date_of_publication, publication_id, quantity, details\n" +
                "FROM book\n" +
                "WHERE isbn =?";
        public static final String INSERT_BOOK = "INSERT INTO book(isbn,name,date_of_publication,publication_id,quantity,details,is_active) VALUES(?,?,?,?,?,?,true)";
        public static final String UPDATE_BOOK = "UPDATE book SET name=?,date_of_publication=?,publication_id=?,quantity=?,details=? WHERE isbn=?";
        public static final String DELETE_BOOK = "DELETE FROM book WHERE isbn = ?";
        public static final String HIDE_BOOK = "UPDATE book SET is_active = false WHERE isbn = ?";
        public static final String IS_AVALIABLE_BOOK = "SELECT name FROM book WHERE quantity>0 AND is_active = true AND isbn=?";
    }

    public static class UserQuery {
        public static final String SELECT_ALL_USERS = "select login, email,role_id,status_id, first_name,last_name, phone\n" +
                "from user";
        public static final String SELECT_USER_BY_LOGIN = "select login, email,role_id,status_id, first_name,last_name, phone\n" +
                "from user\n" +
                "WHERE login = ?";
        public static final String SELECT_ALL_LIBRARIANS = "SELECT login, email,role_id,status_id, first_name,last_name, phone FROM user WHERE role_id=2;";
        public static final String SELECT_USERS_BY_ROLE  = "SELECT login, email,role_id,status_id, first_name,last_name, phone FROM user WHERE role_id=?;";
        public static final String SELECT_PASSWORD_BY_LOGIN = "SELECT password FROM user WHERE login =?";

        public static final String INSERT_USER = "INSERT INTO user values\n" +
                "(?,?,?,?,?,?,?,?);";
        public static final String UPDATE_USER = "UPDATE user SET email=?,role_id=?,status_id=?,first_name=?,last_name=?,phone=? \n" +
                "WHERE login = ?";
        public static final String DELETE_USER = "DELETE FROM user WHERE login = ?";

        public static final String CHANGE_PASSWORD = "UPDATE User SET password = ? WHERE login = ?";
        public static final String CHANGE_LOGIN = "UPDATE User SET login = ? WHERE login = ?";

        public static final String M2M_USERS_ACTIVE_BOOK_ID = "SELECT user_login FROM active_book_has_user WHERE active_book_id =?";
        public static final String M2M_INSERT_BOOK_AND_AUTHOR = "INSERT INTO active_book_has_user VALUES (?,?)";
        public static final String M2M_REMOVE_BOOK_AND_AUTHOR = "DELETE FROM active_book_has_user WHERE active_book_id =?";
    }

    public static class ActiveBookQuery {

        public static final String SELECT_ALL_ACTIVE_BOOKS = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book";
        public static final String SELECT_ACTIVE_BOOKS_BY_USER_LOGIN = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book WHERE user_login = ? AND (subscription_status_id=1 OR subscription_status_id=3)";
        public static final String SELECT_ACTIVE_BOOKS_BY_NAME = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book WHERE name LIKE ?";
        public static final String SELECT_ACTIVE_BOOKS_ORDERS = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book WHERE subscription_status_id=4";
        public static final String SELECT_ACTIVE_BOOK_BY_ID = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book WHERE  active_book_id=?";
        public static final String SELECT_ALL_USERS_ACTIVE_BOOKS = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book WHERE  subscription_status_id=1 OR subscription_status_id=3";
        public static final String SELECT_ACTIVE_BOOK_BY_USER_LOGIN_AND_BOOK_ISBN = "SELECT active_book_id,book_isbn,user_login, subscription_status_id,start_date,end_date,fine FROM active_book WHERE user_login = ? AND book_isbn = ? AND subscription_status_id<>2";

        public static final String INSERT_ACTIVE_BOOK = "Insert Into active_book values\n" +
                "(default,?,?,?,?,?,?);";
        public static final String UPDATE_ACTIVE_BOOK = "UPDATE active_book SET book_isbn=?,user_login=?,subscription_status_id=?,start_date=?,end_date=?,fine=? " +
                "WHERE active_book_id=?";
        public static final String UPDATE_BOOK_BEFORE_GIVE = "UPDATE active_book SET subscription_status_id=?,start_date=?,end_date=?,fine=? " +
                "WHERE active_book_id=?";
        public static final String UPDATE_BOOK_BEFORE_GIVE_BACK = "UPDATE active_book SET subscription_status_id=2 " +
                "WHERE active_book_id = ?";
        public static final String UPDATE_ACTIVE_BOOKS_USER = "UPDATE active_book SET user_login=? WHERE user_login=?";
        public static final String DELETE_ACTIVE_BOOK = "DELETE FROM active_book WHERE active_book_id =?";
    }
}
