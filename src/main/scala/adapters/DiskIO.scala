package adapters

import cats.effect.IO
import domain.ports.Disk
import functional.Nat

import java.nio.file.{Files, Paths}

object DiskIO{
  val nt: Nat[Disk, IO] = new Nat[Disk, IO] {
    override def apply[A](a: Disk[A]): IO[A] = a match {
      case Disk.Read(file) =>
        IO{
          println(s"Read file: $file")
          //Files.readAllBytes(Paths.get(file))
          Array()
        }
      case Disk.Write(file, bytes) => IO(println(s"Write to file: $file"))
      case Disk.Delete(file) =>  IO(println(s"Delete file: $file"))
    }
  }


}
