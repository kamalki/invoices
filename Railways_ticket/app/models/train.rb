class Train < ActiveRecord::Base
  has_one :route
  has_one :train_status
  has_one :consists_of
  has_many :reservations
  has_and_belongs_to_many :stations
end
