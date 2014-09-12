class ConsistsOf < ActiveRecord::Base
  belongs_to :train
  belongs_to :station
end
