package domain.ports

import adapters.{DiskIO, KVStorageInMemoryIO}
import cats.InjectK
import cats.arrow.FunctionK
import cats.data.EitherK
import cats.effect.IO
import domain.ports.DiskAndKVStorage.DiskAndKVStorage



object DiskAndKVStorage {
  type DiskAndKVStorage[T] = EitherK[KVStorage, Disk, T]




  implicit val d = new DiskAlg[DiskAndKVStorage]
  implicit val k = new KVStorageAlg[DiskAndKVStorage]
}


object DiskAlgInjects{
  implicit val i  = new InjectK[Disk,DiskAndKVStorage] {
    override def inj: FunctionK[Disk, DiskAndKVStorage] = {
      λ[FunctionK[Disk, DiskAndKVStorage]]{
        case e@Disk.Write(_, _) => EitherK.rightc(e)
        case e@Disk.Delete(_) =>EitherK.rightc(e)
        case e@Disk.Read(_) =>EitherK.rightc(e)
      }
    }

    override def prj: FunctionK[DiskAndKVStorage, Lambda[α => Option[Disk[α]]]] = ???
  }
}

object KVStorageAlgInjects{
  implicit val i  = new InjectK[KVStorage,DiskAndKVStorage]{
    override def inj: FunctionK[KVStorage, DiskAndKVStorage] =
      //λ[FunctionK[KVStorage, DiskAndKVStorage]](EitherK.leftc)
      λ[FunctionK[KVStorage, DiskAndKVStorage]]{
        case e@KVStorage.Get(_)        => EitherK.leftc(e)
        case e@KVStorage.Delete(_)     => EitherK.leftc(e)
        case e@KVStorage.Exists(_)     => EitherK.leftc(e)
        case e@KVStorage.Put(_,_)      => EitherK.leftc(e)
      }

    override def prj: FunctionK[DiskAndKVStorage, Lambda[α => Option[KVStorage[α]]]] = {
    ???
    }
  }


}