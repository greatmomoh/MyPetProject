package com.example.mypetproject.data.core

interface BaseMapper<M, E, R> {

    fun mapModelToEntity(model: M): E

    fun mapModelToRemote(model: M): R

    fun mapEntityToModel(entity: E): M

    fun mapEntityToRemote(entity: E): R

    fun mapRemoteToModel(remote: R): M

    fun mapRemoteToEntity(remote: R): E


    fun mapEntityToRemoteList(models: List<E>?): List<R> {
        val list = mutableListOf<R>()
        models?.forEach {
            list.add(mapEntityToRemote(it))
        }

        return list
    }

    fun mapEntityToModelList(models: List<E>?): List<M> {
        val list = mutableListOf<M>()
        models?.forEach {
            list.add(mapEntityToModel(it))
        }

        return list
    }

    fun mapModelToEntityList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapModelToEntity(it))
        }

        return list
    }

    fun mapModelToRemoteList(models: List<M>?): List<R> {
        val list = mutableListOf<R>()
        models?.forEach {
            list.add(mapModelToRemote(it))
        }

        return list
    }

    fun mapRemoteToEntityList(models: List<R>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapRemoteToEntity(it))
        }

        return list
    }

    fun mapRemoteToModelList(models: List<R>?): List<M> {
        val list = mutableListOf<M>()
        models?.forEach {
            list.add(mapRemoteToModel(it))
        }

        return list
    }

}