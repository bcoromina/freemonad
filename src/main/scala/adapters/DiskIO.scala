package adapters

import cats.arrow.FunctionK
import cats.effect.IO
import domain.ports.Disk
import functional.Nat


object DiskIO{
  val nt: Nat[Disk, IO] = new Nat[Disk, IO] {
    override def apply[A](a: Disk[A]): IO[A] = naturalTransformation(a)
  }

  val catsDiskNt : FunctionK[Disk,IO] = new FunctionK[Disk, IO] {
    override def apply[A](fa: Disk[A]): IO[A] = naturalTransformation(fa)
  }

  private def naturalTransformation[A](a: Disk[A]): IO[A] = a match {
    case Disk.Read(file) =>
      IO{
        println(s"Disk-Read file: $file")
        //Files.readAllBytes(Paths.get(file))
        Array()
      }
    case Disk.Write(file, bytes) => IO(println(s"Disk-Write to file: $file"))
    case Disk.Delete(file) =>  IO(println(s"Disk-Delete file: $file"))
  }

}
