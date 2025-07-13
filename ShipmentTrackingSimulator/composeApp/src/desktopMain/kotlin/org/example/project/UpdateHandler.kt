package org.example.project

interface UpdateHandler {
    fun handleUpdate(updateString: List<String>)
}

object created : UpdateHandler {
    val type: String = "created"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object delayed : UpdateHandler {
    val type: String = "delayed"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object shipped : UpdateHandler {
    val type: String = "shipped"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object lost : UpdateHandler {
    val type: String = "lost"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object location : UpdateHandler {
    val type: String = "location"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object canceled : UpdateHandler {
    val type: String = "canceled"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object delivered : UpdateHandler {
    val type: String = "delivered"
    override fun handleUpdate(updateString: List<String>) {

    }
}
object noteadded : UpdateHandler {
    val type: String = "noteadded"
    override fun handleUpdate(updateString: List<String>) {

    }
}