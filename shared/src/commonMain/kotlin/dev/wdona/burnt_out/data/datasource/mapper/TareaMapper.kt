package dev.wdona.burnt_out.data.datasource.mapper

import app.cash.sqldelight.Query
import dev.wdona.burnt_out.domain.json.TareaJsonFields
import dev.wdona.burnt_out.shared.domain.Tarea
import dev.wdona.burntout.shared.db.TareaEntity


class TareaMapper {
    companion object {
        fun toDomain(tareaEntity: TareaEntity, idSubtareas: List<Long>): Tarea {
            return Tarea(
                idTarea = tareaEntity.ID_Tarea,
                titulo = tareaEntity.Titulo,
                descripcion = tareaEntity.Descripcion,
                estado = tareaEntity.Estado,
                idTableroPerteneciente = tareaEntity.FK_ID_Tabl,
                idUsuarioAsignado = tareaEntity.FK_ID_Usuario,
                idSubtareas = idSubtareas
            )
        }

        fun toEntity(tarea: Tarea): TareaEntity {
            return TareaEntity(
                ID_Tarea = tarea.idTarea,
                Titulo = tarea.titulo,
                Descripcion = tarea.descripcion,
                Estado = tarea.estado,
                FK_ID_Tabl = tarea.idTableroPerteneciente,
                FK_ID_Usuario = tarea.idUsuarioAsignado
            )
        }

        fun toDomain(tareaEntity: TareaEntity): Tarea {
            return Tarea(
                idTarea = tareaEntity.ID_Tarea,
                titulo = tareaEntity.Titulo,
                descripcion = tareaEntity.Descripcion,
                estado = tareaEntity.Estado,
                idTableroPerteneciente = tareaEntity.FK_ID_Tabl,
                idUsuarioAsignado = tareaEntity.FK_ID_Usuario,
                idSubtareas = emptyList()
            )
        }

        fun toDomainList(tareaEntities: Query<TareaEntity>): List<Tarea> {
            return tareaEntities.executeAsList().map { toDomain(it) }
        }

        fun toDomain(tareaEntity: Query<TareaEntity>): Tarea {
            return toDomain(tareaEntity.executeAsOne())
        }

        fun toJson(tarea: Tarea): String {
            return """{
                "${TareaJsonFields.ID}": ${tarea.idTarea},
                "${TareaJsonFields.NOMBRE}": "${tarea.titulo}",
                "${TareaJsonFields.DESCRIPCION}": "${tarea.descripcion ?: ""}",
                "${TareaJsonFields.ESTADO}": "${tarea.estado}",
                "${TareaJsonFields.ID_TABLERO}": ${tarea.idTableroPerteneciente},
                "${TareaJsonFields.ID_USUARIO_ASIGNADO}": ${tarea.idUsuarioAsignado},
                "${TareaJsonFields.ID_SUBTAREAS}": ${tarea.idSubtareas ?: emptyList()}
            }"""
        }
    }
}