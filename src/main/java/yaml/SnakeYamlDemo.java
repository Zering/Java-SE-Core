package yaml;

import com.google.common.collect.Lists;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghaojie on 2018/1/9.
 * <p>
 * snakeYaml 读取/写入 yaml文件
 */
public class SnakeYamlDemo {

    @SuppressWarnings("unchecked")
    public void yaml2Bean(List<Book> list, String filePath) throws FileNotFoundException {
        String dirPath = this.getClass().getClassLoader().getResource("").getPath();
        InputStream fileStream = new FileInputStream(new File(dirPath + filePath));
        Yaml yaml = new Yaml();
        // Object load = yaml.load(fileStream);
        // System.out.println(load);

        List<Map<String, Object>> origin = (List<Map<String, Object>>) yaml.load(fileStream);
        Book one;
        for (Map<String, Object> obj : origin) {
            one = new Book();
            Object id = obj.get("id");
            one.setId((Integer) id);
            one.setName((String) obj.get("name"));
            one.setAuthor((String) obj.get("author"));
            one.setCategory((String) obj.get("category"));
            one.setPublish((String) obj.get("publish"));
            one.setPrice((Integer) obj.get("price"));
            list.add(one);
        }
        // Book book = yaml.loadAs(fileStream, Book.class);
        // Construct construct

    }

    public static void main(String[] args) throws FileNotFoundException {
        SnakeYamlDemo snakeYaml = new SnakeYamlDemo();
        List<Book> bookList = Lists.newArrayList();
        snakeYaml.yaml2Bean(bookList, "/yaml/book.yaml");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
