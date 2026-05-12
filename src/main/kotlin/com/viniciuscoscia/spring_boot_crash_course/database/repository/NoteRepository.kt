package com.viniciuscoscia.spring_boot_crash_course.database.repository

import com.viniciuscoscia.spring_boot_crash_course.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository: MongoRepository<Note, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<Note>
    fun id(id: ObjectId): MutableList<Note>
}