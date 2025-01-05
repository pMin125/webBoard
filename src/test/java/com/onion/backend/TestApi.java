package com.onion.backend;

import com.onion.backend.entity.Article;
import com.onion.backend.entity.Comment;
import com.onion.backend.entity.User;
import com.onion.backend.pojo.SendCommentNotification;
import com.onion.backend.pojo.WriteComment;
import com.onion.backend.repository.CommentRepository;
import com.onion.backend.service.RabbitMQReceiver;
import com.onion.backend.service.RabbitMQSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BackendApplication.class)
class TestApi {
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private CommentRepository commentRepository;  // 실제 Repository 주입

    @Mock
    private RabbitMQReceiver rabbitMQReceiver;  // 실제 Receiver 대신 Mock 사용

    @Test
    void testSendMultipleCommentNotifications() {
        // 대량 댓글 삽입
        for (long i = 1; i <= 3; i++) {
            // 댓글 데이터 설정
            User author = new User(1L, "user1");  // 작성자 설정
            Article article = new Article(1L, "article1");  // 게시글 설정

            Comment comment = new Comment();
            comment.setAuthor(author);
            comment.setArticle(article);
            comment.setContent("Test Comment " + i);

            // 댓글을 데이터베이스에 저장
            commentRepository.save(comment);
            WriteComment writeComment = new WriteComment();
            writeComment.setCommentId(comment.getId());
            rabbitMQSender.send(writeComment);
        }
    }
}
