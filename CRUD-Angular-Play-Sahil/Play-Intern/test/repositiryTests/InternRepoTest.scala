package repositiryTests

/**
  * Created by sahil on 3/21/16.
  */

import models.Intern
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import repositories.InternRepo
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future


class InternRepoTest extends Specification {

  "InternRepoTest" should{

    def internRepo(implicit app: Application):InternRepo = Application.instanceCache[InternRepo].apply(app)

    "Get Intern Details By Id" in new WithApplication{

      val result = await(internRepo.internById(1))
      result === Intern("sahil","test1@knoldus.com","9871211045","Delhi","9958870783",1)
    }

    "Get All Interns" in new WithApplication{

      val result = await(internRepo.getAll)
      result === List(Intern("sahil","test1@knoldus.com","9871211045","Delhi","9958870783",1), Intern("varun","test2@knoldus.com","9958870783","Kanpur","9871211045",2))
    }

    "Edit Intern" in new WithApplication{

      val result = await(internRepo.update(Intern("sahil sawhney","test1@knoldus.com","9871211045","Delhi","9958870783",1)))
      result === 1
    }

    "Delete Intern By Id" in new WithApplication{

      val result = await(internRepo.delete(1))
      result === 1
    }

    "Insert New Intern" in new WithApplication{

      val result = await(internRepo.insert(Intern("akshay","test3@knoldus.com","9871211045","Himachal","9958870783",3)))
      result === 1
    }

  }

  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)
}
