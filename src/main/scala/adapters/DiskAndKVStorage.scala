package adapters

import cats.arrow.FunctionK
import cats.data.EitherK
import cats.effect.IO
import domain.ports.{Disk, KVStorage}

object DiskAndKVStorage {
  val ioInterpreter: FunctionK[EitherK[KVStorage, Disk, *], IO] = KVStorageInMemoryIO.catsKVStorageNt or DiskIO.catsDiskNt
}
