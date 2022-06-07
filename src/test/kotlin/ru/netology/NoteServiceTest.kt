package ru.netology

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before



class NoteServiceTest {


    @After
    fun clear() {
        NoteService.clearAll()
    }

    @Test
    fun add() {
        val expectedId = 1
        val id = NoteService.add(Note())
        assertEquals(expectedId, id)
    }

    @Test
    fun afterDeleteReturnsTrue() {
        NoteService.add(Note())
        NoteService.add(Note())
        NoteService.add(Note())
        val result = NoteService.delete(Note(id = 1))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterDelete() {
        NoteService.add(Note())
        NoteService.add(Note())
        NoteService.delete(Note(id = 10))
    }

    @Test
    fun afterEditReturnsTrue() {
        NoteService.add(Note())
        val result = NoteService.edit(Note(id = 1, title = "result title"))
        assertTrue(result)
    }

    @Test(expected = NoteOrCommentNotFoundException::class)
    fun exceptionAfterEdit() {
        NoteService.add(Note())
        NoteService.edit(Note(id = 10, title = "result title"))
    }

    @Test
    fun get() {
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
    fun exceptionAfterGetById() {
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
}