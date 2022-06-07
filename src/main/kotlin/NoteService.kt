package ru.netology
object NoteService : BaseService<Note>() {

    override fun add(item: Note): Int {
        val updateNote = item.copy(id = getLastId())
        getItems().add(updateNote)
        return updateNote.id
    }

    override fun delete(item: Note): Boolean {
        for (note in NoteService.getItems()) {
            if (note.id == item.id) {
                getItems().remove(note)
                for (comment in CommentService.getItems()) {
                    if (comment.noteId == note.id) {
                        CommentService.getItems().remove(comment)
                    }
                }
                return true
            }
        }
        throw NoteOrCommentNotFoundException("This note wasn't found")
    }

    override fun edit(item: Note): Boolean {
        for ((index, note) in NoteService.getItems().withIndex()) {
            if (note.id == item.id) {
                NoteService.getItems()[index] = item
                return true
            }
        }
        throw NoteOrCommentNotFoundException("This note wasn't found")
    }

    fun get(): MutableList<Note> {
        return NoteService.getItems()
    }

    fun getById(id: Int): Note {
        for (note in NoteService.getItems()) {
            if (id == note.id) {
                return note
            }
        }
        throw NoteOrCommentNotFoundException("This note wasn't found")
    }
}