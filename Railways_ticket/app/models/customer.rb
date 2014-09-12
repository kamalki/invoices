class Customer < ActiveRecord::Base
  has_one :reservation
  belongs_to :station
end
