package poc.modifier

import java.util.{Calendar, Date}

class Leader extends Modifier {
  var broadcastTime: Date = Calendar.getInstance().getTime
}
