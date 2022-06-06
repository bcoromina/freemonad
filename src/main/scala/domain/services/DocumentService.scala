package domain.services


import cats.InjectK
import cats.data.EitherK
import cats.free.Free
import domain.ports.Disk.DiskM
import domain.ports.DiskAndKVStorage.DiskAndKVStorage
import domain.ports.{Disk, DiskAlg, DiskCats, KVStorage, KVStorageAlg}
import functional.FreeMonadSyntax._

object DocumentService {

  def doSomeOperations(file: String): DiskM[Unit] = {
    for{
      contents <- Disk.read(file)
      _ <- Disk.write(file, contents ++ "hola".getBytes)
      _ <- Disk.delete("")
    }yield ()
  }



  def doSometingElse(file: String)(implicit D: DiskAlg[DiskAndKVStorage], K: KVStorageAlg[DiskAndKVStorage]): Free[DiskAndKVStorage, Unit] = {
    for{
      contents <- D.read(file)
      _ <- K.put(file, new String(contents))
      _ <- D.write(file, contents ++ "hola".getBytes)
      _ <- D.delete("")
    }yield ()
  }

  //https://medium.com/@agaro1121/free-monad-vs-tagless-final-623f92313eac
  //https://underscore.io/blog/posts/2017/03/29/free-inject.html
  //https://perevillega.com/understanding-free-monads/
  //https://blog.higher-order.com/assets/trampolines.pdf
  //https://www.youtube.com/watch?v=IhVdU4Xiz2U

  //https://www.47deg.com/blog/fp-for-the-average-joe-part3-free-monads/
  //https://underscore.io/blog/posts/2017/03/29/free-inject.html
}
