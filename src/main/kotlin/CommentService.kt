package ru.netology
object CommentService : BaseService<Comment>() {
    override fun add(item: Comment): Int {
        for (note in NoteService.getItems()) {
            if (note.id == item.noteId) {
                val updateComment = item.copy(commentId = getLastId())
                getItems().add(updateComment)
                note.comments++
                return updateComment.commentId
            }
        }
        throw NoteOrCommentNotFoundException("This comment wasn't created")
    }

    override fun delete(item: Comment): Boolean {
        for ((index, comment) in CommentService.getItems().withIndex()) {
            if (comment.commentId == item.commentId && !comment.wasDeleted) {
                val deletedComment = item.copy(wasDeleted = true, noteId = comment.noteId)
                CommentService.getItems()[index] = deletedComment
                return true
            }
        }
        throw NoteOrCommentNotFoundException("This comment wasn't found or was already deleted")
    }

    override fun edit(item: Comment): Boolean {
        for ((index, comment) in CommentService.getItems().withIndex()) {
            if (comment.commentId == item.commentId && !comment.wasDeleted) {
                CommentService.getItems()[index] = item
                return true
            }
        }
        return false
    }

    fun getComments(id: Int): MutableList<Comment> {
        val comments = mutableListOf<Comment>()
        for (comment in CommentService.getItems()) {
            if (comment.noteId == id && !comment.wasDeleted) {
                comments += comment
            }
        }
        return comments
    }

    fun restoreComment(commentId: Int): Boolean {
        for ((index, comment) in CommentService.getItems().withIndex()) {
            if (comment.commentId == commentId && comment.wasDeleted) {
                val restoreComment = comment.copy(wasDeleted = false)
                CommentService.getItems()[index] = restoreComment
                return true
            }
        }
        throw NoteOrCommentNotFoundException("This comment wasn't restored")
    }
}