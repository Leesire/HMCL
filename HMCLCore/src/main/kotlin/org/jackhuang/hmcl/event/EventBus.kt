/*
 * Hello Minecraft! Launcher.
 * Copyright (C) 2017  huangyuhui <huanghongxun2008@126.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see {http://www.gnu.org/licenses/}.
 */
package org.jackhuang.hmcl.event

import org.jackhuang.hmcl.task.Scheduler
import java.util.*

class EventBus {
    val events = HashMap<Class<*>, EventManager<*>>()

    @Suppress("UNCHECKED_CAST")
    fun <T : EventObject> channel(classOfT: Class<T>): EventManager<T> {
        if (!events.containsKey(classOfT))
            events.put(classOfT, EventManager<T>(Scheduler.COMPUTATION))
        return events[classOfT] as EventManager<T>
    }

    inline fun <reified T: EventObject> channel() = channel(T::class.java)

    fun fireEvent(obj: EventObject) {
        channel(obj.javaClass).fireEvent(obj)
    }

}

val EVENT_BUS = EventBus()