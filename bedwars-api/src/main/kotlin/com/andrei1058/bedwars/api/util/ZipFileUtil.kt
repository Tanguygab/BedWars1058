package com.andrei1058.bedwars.api.util

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

object ZipFileUtil {
    @Throws(IOException::class)
    fun zipDirectory(dir: File, zipFile: File) {
        ZipOutputStream(FileOutputStream(zipFile)).use {
            zipSubDirectory("", dir, it)
        }
    }

    @Throws(IOException::class)
    private fun zipSubDirectory(basePath: String?, dir: File, out: ZipOutputStream) {
        val files = dir.listFiles() ?: return

        val buffer = ByteArray(4096)
        for (file in files) {
            if (file.isDirectory) {
                val path = basePath + file.name + "/"
                out.putNextEntry(ZipEntry(path))
                zipSubDirectory(path, file, out)
                out.closeEntry()
                continue
            }

            out.putNextEntry(ZipEntry(basePath + file.name))
            var length: Int
            FileInputStream(file).use { stream ->
                while (stream.read(buffer).also { length = it } > 0) {
                    out.write(buffer, 0, length)
                }
                stream.close()
            }
            out.closeEntry()
        }
    }

    @Throws(IOException::class)
    fun unzipFileIntoDirectory(file: File, jiniHomeParentDir: File) {
        if (!file.exists()) return

        val buffer = ByteArray(1024)
        val zipFile = ZipFile(file)
        val files = zipFile.entries()

        while (files.hasMoreElements()) {
            try {
                val entry = files.nextElement() as ZipEntry
                val `in` = zipFile.getInputStream(entry)

                val file = File(jiniHomeParentDir.absolutePath, entry.name)

                if (entry.isDirectory) {
                    file.mkdirs()
                    continue
                }

                file.parentFile.mkdirs()
                file.createNewFile()

                FileOutputStream(file).use { stream ->
                    var length: Int
                    while (`in`.read(buffer).also { length = it } != -1) {
                        stream.write(buffer, 0, length)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
