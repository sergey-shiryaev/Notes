package ru.netology

import org.junit.After
import org.junit.Test

import org.junit.Assert.*


class ServiceTest {


    @After
    fun clear() {
        NoteService.clearAll()
        CommentService.clearAll()
    }

    @Test
    fun addNote() {
        val expectedId = 1
        val id = NoteService.add(Note())
        assertEquals(expectedId, id)
    }

    @Test
    fun afterDeleteReturnsTrueNote() {
        NoteService.add(Note())
        NoteService.add(Note())
        NoteService.add(Note())
        val result = NoteService.delete(Note(id = 1))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterDeleteNote() {
        NoteService.add(Note())
        NoteService.add(Note())
        NoteService.delete(Note(id = 10))
    }

    @Test
    fun afterEditReturnsTrueNote() {
        NoteService.add(Note())
        val result = NoteService.edit(Note(id = 1, title = "result title"))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterEditNote() {
        NoteService.add(Note())
        NoteService.edit(Note(id = 10, title = "result title"))
    }

    @Test
    fun getNote() {
        val note1 = Note(
            id = 1,
            title = "",
            text = "",
            comments = 0
        )
        val note2 = Note(
            id = 2,
            title = "",
            text = "",
            comments = 0
        )
        val note3 = Note(
            id = 3,
            title = "",
            text = "",
            comments = 0
        )
        val expectedNotes = mutableListOf<Note>()
        expectedNotes += note1
        expectedNotes += note2
        expectedNotes += note3
        NoteService.add(note1)
        NoteService.add(note1)
        NoteService.add(note1)
        val notes = NoteService.get()
        assertEquals(expectedNotes, notes)
    }

    @Test
    fun getByIdReturnsNote() {
        val expectedNote = Note(
            id = 1,
            title = "",
            text = "",
            comments = 0
        )
        val note2 = Note(
            id = 2,
            title = "",
            text = "",
            comments = 0
        )
        NoteService.add(expectedNote)
        NoteService.add(note2)
        val resultNote = NoteService.getById(1)
        assertEquals(expectedNote, resultNote)
    }
    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterGetByIdNote() {
        val note1 = Note(
            id = 1,
            title = "",
            text = "",
            comments = 0
        )
        val note2 = Note(
            id = 2,
            title = "",
            text = "",
            comments = 0
        )
        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.getById(10)
    }

    @Test
    fun afterAddReturnsIdComment() {
        NoteService.add(Note())
        val expectedId = 1
        val id = CommentService.add(Comment(noteId = 1))
        assertEquals(expectedId, id)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterAddComment() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 100))
    }

    @Test
    fun afterDeleteReturnsTrueComment() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        val result = CommentService.delete(Comment(commentId = 1))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterDeleteComment() {
        NoteService.add(Note())
        CommentService.add(Comment(noteId = 1))
        CommentService.delete(Comment(commentId = 100))
    }

    @Test
    fun afterEditReturnsTrueComment() {
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
        CommentService.delete(Comment(commentId = 1))
        val result = CommentService.restoreComment(1)
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterRestoreComment() {
        NoteService.add(Note())
        CommentService.delete(Comment(commentId = 1))
        CommentService.restoreComment(2)
    }
}