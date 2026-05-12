package com.viniciuscoscia.spring_boot_crash_course.controllers

import com.viniciuscoscia.spring_boot_crash_course.database.model.Note
import com.viniciuscoscia.spring_boot_crash_course.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.time.Clock

// POST http://localhost:8085/notes
// GET http://localhost:8085/notes?ownerId=123

@RestController
@RequestMapping("/notes")
class NoteController(
    val repository: NoteRepository
) {

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val ownerId: String,
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: String,
        val ownerId: String
    )

    @PostMapping
    fun save(@RequestBody body: NoteRequest): NoteResponse {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Clock.System.now(),
                ownerId = ObjectId(body.ownerId)
            )
        )
        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }
}

private fun Note.toResponse(): NoteController.NoteResponse {
    return NoteController.NoteResponse(
        id = this.id.toHexString(),
        title = this.title,
        content = this.content,
        color = this.color,
        createdAt = this.createdAt.toString(),
        ownerId = this.ownerId.toHexString()
    )
}
