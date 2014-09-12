class CreateTrainsStations < ActiveRecord::Migration
  def change
    create_table :trains_stations, :id => false do |t|
    t.integer :train_id
    t.integer :station_id
    end
  end
end
