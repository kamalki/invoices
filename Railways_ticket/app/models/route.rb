class Route < ActiveRecord::Base
  belongs_to_many :trains
end
