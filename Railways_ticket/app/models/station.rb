class Station < ActiveRecord::Base
  has_and_belongs_to_many :trains
  has_many :customers
  has one :consists_of
end
