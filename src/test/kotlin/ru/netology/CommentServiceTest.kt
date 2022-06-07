package ru.netology

import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class CommentServiceTest {

    @After
    fun clear() {
        CommentService.clearAll()
    }

    @Test
    fun afterAddReturnsId() {
        NoteService.add(Note())
        val expectedId = 1
        val id = CommentService.add(Comment(noteId = 1))
        assertEquals(expectedId, id)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterAdd() {
        NoteService.add(Note())
        val id = CommentService.add(Comment(noteId = 100))
    }

    @Test
    fun afterDeleteReturnsTrue() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        val result = CommentService.delete(Comment(commentId = 1))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterDelete() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        CommentService.delete(Comment(commentId = 100))
    }

    @Test
    fun afterEditReturnsTrue() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        val result = CommentService.edit(Comment(commentId = 1, message = "message"))
        assertTrue(result)
    }

    @Test
    fun afterEditReturnsFalse() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        val result = CommentService.edit(Comment(commentId = 100, message = "message"))
        assertFalse(result)
    }

    @Test
    fun returnsComments() {
        val comment1 = Comment(
            commentId = 1,
            noteId = 1,
            message = "",
            wasDeleted = false
        )
        val comment2 = Comment(
            commentId = 2,
            noteId = 1,
            message = "",
            wasDeleted = false
        )
        val comments = mutableListOf<Comment>()
        comments += comment1
        comments += comment2
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        CommentService.add(Comment(noteId = 1))
        val resultComments = CommentService.getComments(1)
        assertEquals(comments, resultComments)
    }

    @Test
    fun restoreCommentReturnsTrue() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        val result = CommentService.delete(Comment(commentId = 1))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterRestoreComment() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        CommentService.delete(Comment(commentId = 100))
    }
}